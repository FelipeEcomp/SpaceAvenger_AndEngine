package activity.projeto;

import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXLoader.ITMXTilePropertiesListener;
import org.andengine.extension.tmx.TMXProperties;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.extension.tmx.TMXTileProperty;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.controller.MultiTouch;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.Constants;
import org.andengine.util.debug.Debug;
import org.andengine.util.math.MathUtils;





import android.opengl.GLES20;
import android.widget.Toast;

import android.widget.Toast;


public class navegacaoActivity extends SimpleBaseGameActivity{

	  // ===========================================================
		// ===========================================================
			// Constants
			// ===========================================================

			private static final int CAMERA_WIDTH = 480;
			private static final int CAMERA_HEIGHT = 320;

			// ===========================================================
			// Fields
			// ===========================================================

			private BoundCamera mBoundChaseCamera;

			private BitmapTextureAtlas mBitmapTextureAtlas;
			
			private ITextureRegion mFaceTextureRegion;

			private TMXTiledMap mTMXTiledMap;
			
			private BitmapTextureAtlas mOnScreenControlTexture;
			private ITextureRegion mOnScreenControlBaseTextureRegion;
			private ITextureRegion mOnScreenControlKnobTextureRegion;

			private boolean mPlaceOnScreenControlsAtDifferentVerticalLocations = false;

			
			protected int mCactusCount;

			// ===========================================================
			// Constructors
			// ===========================================================

			// ===========================================================
			// Getter & Setter
			// ===========================================================

			// ===========================================================
			// Methods for/from SuperClass/Interfaces
			// ===========================================================

			@Override
			public EngineOptions onCreateEngineOptions() {
				Toast.makeText(this, "Testando o mapa.", Toast.LENGTH_LONG).show();

				this.mBoundChaseCamera = new BoundCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

				if(MultiTouch.isSupported(this)) {
					if(MultiTouch.isSupportedDistinct(this)) {
						Toast.makeText(this, "MultiTouch detected --> Both controls will work properly!", Toast.LENGTH_SHORT).show();
					} else {
						this.mPlaceOnScreenControlsAtDifferentVerticalLocations = true;
						Toast.makeText(this, "MultiTouch detected, but your device has problems distinguishing between fingers.\n\nControls are placed at different vertical locations.", Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(this, "Sorry your device does NOT support MultiTouch!\n\n(Falling back to SingleTouch.)\n\nControls are placed at different vertical locations.", Toast.LENGTH_LONG).show();
				}
				
				final EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mBoundChaseCamera);
				engineOptions.getTouchOptions().setNeedsMultiTouch(true);

				
				return  engineOptions;
			}

			@Override
			public void onCreateResources() {
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

				this.mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 72, 128, TextureOptions.DEFAULT);
				this.mFaceTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "nave.png", 0, 0);
				this.mBitmapTextureAtlas.load();
				
				this.mOnScreenControlTexture = new BitmapTextureAtlas(this.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
				this.mOnScreenControlBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, this, "onscreen_control_base.png", 0, 0);
				this.mOnScreenControlKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, this, "onscreen_control_knob.png", 128, 0);
				this.mOnScreenControlTexture.load();
				this.mBitmapTextureAtlas.load();
			}

			@Override
			public Scene onCreateScene() {
				this.mEngine.registerUpdateHandler(new FPSLogger());

				final Scene scene = new Scene();

				try {
					final TMXLoader tmxLoader = new TMXLoader(this.getAssets(), this.mEngine.getTextureManager(), TextureOptions.BILINEAR_PREMULTIPLYALPHA, this.getVertexBufferObjectManager(), new ITMXTilePropertiesListener() {
						@Override
						public void onTMXTileWithPropertiesCreated(final TMXTiledMap pTMXTiledMap, final TMXLayer pTMXLayer, final TMXTile pTMXTile, final TMXProperties<TMXTileProperty> pTMXTileProperties) {
							/* We are going to count the tiles that have the property "cactus=true" set. */
							if(pTMXTileProperties.containsTMXProperty("cactus", "true")) {
								navegacaoActivity.this.mCactusCount++;
							}
						}
					});
					this.mTMXTiledMap = tmxLoader.loadFromAsset("tmx/desert.tmx");

					this.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(navegacaoActivity.this, "Cactus count in this TMXTiledMap: " + navegacaoActivity.this.mCactusCount, Toast.LENGTH_LONG).show();
						}
					});
				} catch (final TMXLoadException e) {
					Debug.e(e);
				}

				final TMXLayer tmxLayer = this.mTMXTiledMap.getTMXLayers().get(0);
				scene.attachChild(tmxLayer);

				/* Make the camera not exceed the bounds of the TMXEntity. */
				this.mBoundChaseCamera.setBounds(0, 0, tmxLayer.getHeight(), tmxLayer.getWidth());
				this.mBoundChaseCamera.setBoundsEnabled(true);

				/* Calculate the coordinates for the face, so its centered on the camera. */
				final float centerX = (CAMERA_WIDTH - this.mFaceTextureRegion.getWidth()) / 2;
				final float centerY = (CAMERA_HEIGHT - this.mFaceTextureRegion.getHeight()) / 2;

				final Sprite face = new Sprite(centerX, centerY + 42, this.mFaceTextureRegion, this.getVertexBufferObjectManager()) {
					@Override
					public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
						this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
						return true;
					}
				};;
				final PhysicsHandler physicsHandler = new PhysicsHandler(face);
				face.registerUpdateHandler(physicsHandler);
				this.mBoundChaseCamera.setChaseEntity(face);
				scene.attachChild(face);
				scene.registerTouchArea(face);
				
				
				

				/* Velocity control (left). */
				final float x1 = 0;
				final float y1 = CAMERA_HEIGHT - this.mOnScreenControlBaseTextureRegion.getHeight();
				final AnalogOnScreenControl velocityOnScreenControl = new AnalogOnScreenControl(x1, y1, this.mBoundChaseCamera, this.mOnScreenControlBaseTextureRegion, this.mOnScreenControlKnobTextureRegion, 0.1f, this.getVertexBufferObjectManager(), new IAnalogOnScreenControlListener() {
					@Override
					public void onControlChange(final BaseOnScreenControl pBaseOnScreenControl, final float pValueX, final float pValueY) {
						physicsHandler.setVelocity(pValueX * 100, pValueY * 100);
					}

					@Override
					public void onControlClick(final AnalogOnScreenControl pAnalogOnScreenControl) {
						/* Nothing. */
					}
				});
				velocityOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
				velocityOnScreenControl.getControlBase().setAlpha(0.5f);

				scene.setChildScene(velocityOnScreenControl);


				/* Rotation control (right). */
				final float y2 = (this.mPlaceOnScreenControlsAtDifferentVerticalLocations) ? 0 : y1;
				final float x2 = CAMERA_WIDTH - this.mOnScreenControlBaseTextureRegion.getWidth();
				final AnalogOnScreenControl rotationOnScreenControl = new AnalogOnScreenControl(x2, y2, this.mBoundChaseCamera, this.mOnScreenControlBaseTextureRegion, this.mOnScreenControlKnobTextureRegion, 0.1f, this.getVertexBufferObjectManager(), new IAnalogOnScreenControlListener() {
					@Override
					public void onControlChange(final BaseOnScreenControl pBaseOnScreenControl, final float pValueX, final float pValueY) {
						if(pValueX == x1 && pValueY == x1) {
							face.setRotation(x1);
						} else {
							face.setRotation(MathUtils.radToDeg((float)Math.atan2(pValueX, -pValueY)));
						}
					}

					@Override
					public void onControlClick(final AnalogOnScreenControl pAnalogOnScreenControl) {
						/* Nothing. */
					}
				});
				rotationOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
				rotationOnScreenControl.getControlBase().setAlpha(0.5f);

				velocityOnScreenControl.setChildScene(rotationOnScreenControl);
				
				
				


				scene.registerUpdateHandler(new IUpdateHandler() {
					@Override
					public void reset() { }

					@Override
					public void onUpdate(final float pSecondsElapsed) {
						
					}
				});
				
		
				

				return scene;
			}

			// ===========================================================
			// Methods
			// ===========================================================

			// ===========================================================
			// Inner and Anonymous Classes
			// ===========================================================
			}
