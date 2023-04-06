package com.abhiram.minions_ar

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.AugmentedFace
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.AugmentedFaceNode
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.ArSceneView


class MainActivity : AppCompatActivity() {
    private lateinit var arFragment: ArSceneView
    private var modelRenderable: ModelRenderable? = null
    private var isAdded = false
    private val faceNodeMap: HashMap<AugmentedFace, AugmentedFaceNode> = HashMap()

    //3d model credit : google.poly.com
//    private val Model_URL = "https://modelviewer.dev/shared-assets/models/Astronaut.glb"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gun = this.findViewById<TextView>(R.id.gun)
        val invento = this.findViewById<TextView>(R.id.invento)
        val minion = this.findViewById<TextView>(R.id.minion)
        val mustang = this.findViewById<TextView>(R.id.mustang)
        val m4 = this.findViewById<TextView>(R.id.m4)
        val face = this.findViewById<TextView>(R.id.face)
        val shelby = this.findViewById<TextView>(R.id.shelby)
        arFragment = supportFragmentManager.findFragmentById(R.id.ux_fragment)
        ARScene()
        setUpMinion()
        setUpPlane()
        Toast.makeText(applicationContext,"Minion selected",Toast.LENGTH_SHORT).show()
        gun.setOnClickListener{
            Toast.makeText(applicationContext,"Gun selected",Toast.LENGTH_SHORT).show()
            setUpGun()
            setUpPlane()
        }

        minion.setOnClickListener {
            Toast.makeText(applicationContext,"Minion selected",Toast.LENGTH_SHORT).show()
            setUpMinion()
            setUpPlane()
        }

        invento.setOnClickListener {
            Toast.makeText(applicationContext,"Invento selected",Toast.LENGTH_SHORT).show()
            setUpInvento()
            setUpPlane()
        }

        mustang.setOnClickListener {
            Toast.makeText(applicationContext,"Mustang selected",Toast.LENGTH_SHORT).show()
            setUpMustang()
            setUpPlane()
        }

        m4.setOnClickListener {
            Toast.makeText(applicationContext,"M4 selected",Toast.LENGTH_SHORT).show()
            setUpM4()
            setUpPlane()
        }
        shelby.setOnClickListener {
            Toast.makeText(applicationContext,"Shelby selected",Toast.LENGTH_SHORT).show()
            setUpShelby()
            setUpPlane()
        }

        face.setOnClickListener {
            Toast.makeText(applicationContext,"Face selected",Toast.LENGTH_SHORT).show()
            setFace()
        }

    }
//
//    fun setFace(){
//        val customArFragment: CustomARFragment? =
//            supportFragmentManager.findFragmentById(R.id.ux_fragment) as CustomARFragment?
//
//        // Use ModelRenderable.Builder to load the *.sfb
//        // models at runtime.
//        // Load the face regions renderable.
//        // To ensure that the asset doesn't cast or receive
//        // shadows in the scene, ensure that setShadowCaster
//        // and setShadowReceiver are both set to false.
//
//        // Use ModelRenderable.Builder to load the *.sfb
//        // models at runtime.
//        // Load the face regions renderable.
//        // To ensure that the asset doesn't cast or receive
//        // shadows in the scene, ensure that setShadowCaster
//        // and setShadowReceiver are both set to false.
//        ModelRenderable.builder()
//            .setSource(this, R.raw.fox_face)
//            .build()
//            .thenAccept { rendarable: ModelRenderable? ->
//                modelRenderable = rendarable
//                modelRenderable!!.isShadowCaster = false
//                modelRenderable!!.isShadowReceiver = false
//            }
//            .exceptionally { throwable: Throwable? ->
//                Toast.makeText(this, "error loading model", Toast.LENGTH_SHORT).show()
//                null
//            }
//
//        // Load the face mesh texture.(2D texture on face)
//        // Save the texture(.png file) in drawable folder.
//
//        // Load the face mesh texture.(2D texture on face)
//        // Save the texture(.png file) in drawable folder.
////        Texture.builder()
////            .setSource(this, R.drawable.fox_face_mesh_texture)
////            .build()
////            .thenAccept { textureModel -> this.texture = textureModel }
////            .exceptionally { throwable ->
////                Toast.makeText(this, "cannot load texture", Toast.LENGTH_SHORT).show()
////                null
////            }
//
//        assert(customArFragment != null)
//
//        // This is important to make sure that the camera
//        // stream renders first so that the face mesh
//        // occlusion works correctly.
//
//        // This is important to make sure that the camera
//        // stream renders first so that the face mesh
//        // occlusion works correctly.
//        customArFragment?.arSceneView?.scene?.addOnUpdateListener { frameTime ->
//            if (modelRenderable == null) {
//                return@addOnUpdateListener
//            }
//            val frame: Frame = customArFragment.arSceneView?.arFrame!!
//
//            // Render the effect for the face Rendering the effect involves these steps:
//            // 1.Create the Sceneform face node.
//            // 2.Add the face node to the Sceneform scene.
//            // 3.Set the face region Renderable. Extracting the face mesh and
//            // rendering the face effect is added to a listener on
//            // the scene that gets called on every processed camera frame.
//            val augmentedFaces: Collection<AugmentedFace> =
//                frame.getUpdatedTrackables(AugmentedFace::class.java)
//
//            // Make new AugmentedFaceNodes for any new faces.
//            for (augmentedFace in augmentedFaces) {
//                if (isAdded) return@addOnUpdateListener
//                val augmentedFaceMode = AugmentedFaceNode(augmentedFace)
//                augmentedFaceMode.setParent(customArFragment.arSceneView.scene)
//                augmentedFaceMode.faceRegionsRenderable = modelRenderable
//                faceNodeMap.put(augmentedFace, augmentedFaceMode)
//                isAdded = true
//
//                // Remove any AugmentedFaceNodes associated with
//                // an AugmentedFace that stopped tracking.
//                val iterator: MutableIterator<Map.Entry<AugmentedFace, AugmentedFaceNode>> =
//                    faceNodeMap.entries.iterator()
//                val (face, node) = iterator.next()
//                while (face.trackingState == TrackingState.STOPPED) {
//                    node.setParent(null)
//                    iterator.remove()
//                }
//            }
//        }
//    }
//
//    fun setUpMinion(){
//        ModelRenderable.builder()
//            .setSource(this, R.raw.minion )
//            .setIsFilamentGltf(true)
//            .build()
//            .thenAccept {renderable: ModelRenderable ->
//                modelRenderable = renderable}
//            .exceptionally { throwable: Throwable? ->
//                Log.i("Model", "cant load")
//                Toast.makeText(this@MainActivity, "Model can't be Loaded", Toast.LENGTH_SHORT)
//                    .show()
//                null
//            }
//    }
//
//    fun setUpM4(){
//        ModelRenderable.builder()
//            .setSource(this, R.raw.classic_m4 )
//            .setIsFilamentGltf(true)
//            .build()
//            .thenAccept {renderable: ModelRenderable ->
//                modelRenderable = renderable}
//            .exceptionally { throwable: Throwable? ->
//                Log.i("Model", "cant load")
//                Toast.makeText(this@MainActivity, "Model can't be Loaded", Toast.LENGTH_SHORT)
//                    .show()
//                null
//            }
//    }
//
//    fun setUpMustang(){
//        ModelRenderable.builder()
//            .setSource(this, R.raw.ford_mustang_shelby_gt500 )
//            .setIsFilamentGltf(true)
//            .build()
//            .thenAccept {renderable: ModelRenderable ->
//                modelRenderable = renderable}
//            .exceptionally { throwable: Throwable? ->
//                Log.i("Model", "cant load")
//                Toast.makeText(this@MainActivity, "Model can't be Loaded", Toast.LENGTH_SHORT)
//                    .show()
//                null
//            }
//    }
//    fun setUpShelby(){
//        ModelRenderable.builder()
//            .setSource(this, R.raw.ford_mustang_shelby_2012 )
//            .setIsFilamentGltf(true)
//            .build()
//            .thenAccept {renderable: ModelRenderable ->
//                modelRenderable = renderable}
//            .exceptionally { throwable: Throwable? ->
//                Log.i("Model", "cant load")
//                Toast.makeText(this@MainActivity, "Model can't be Loaded", Toast.LENGTH_SHORT)
//                    .show()
//                null
//            }
//    }
//
//    fun setUpInvento(){
//        ModelRenderable.builder()
//            .setSource(this, R.raw.invento )
//            .setIsFilamentGltf(true)
//            .build()
//            .thenAccept {renderable: ModelRenderable ->
//                modelRenderable = renderable}
//            .exceptionally { throwable: Throwable? ->
//                Log.i("Model", "cant load")
//                Toast.makeText(this@MainActivity, "Model can't be Loaded", Toast.LENGTH_SHORT)
//                    .show()
//                null
//            }
//    }
//
//    fun setUpGun(){
//        ModelRenderable.builder()
//            .setSource(this, R.raw.gun_edited)
//            .setIsFilamentGltf(true)
//            .build()
//            .thenAccept {renderable: ModelRenderable ->
//                modelRenderable = renderable}
//            .exceptionally { throwable: Throwable? ->
//                Log.i("Model", "cant load")
//                Toast.makeText(this@MainActivity, "Model can't be Loaded", Toast.LENGTH_SHORT)
//                    .show()
//                null
//            }
//    }

//    private fun setUpPlane() {
//        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
//            val anchor: Anchor = hitResult.createAnchor()
//            val anchorNode = AnchorNode(anchor)
//            anchorNode.setParent(arFragment.arSceneView.scene)
//            createModel(anchorNode)
//        }
//    }
//
//    private fun createModel(anchorNode: AnchorNode) {
//        val node = TransformableNode(arFragment.transformationSystem)
//        node.setParent(anchorNode)
//        node.renderable = modelRenderable
//        node.select()
//    }
}