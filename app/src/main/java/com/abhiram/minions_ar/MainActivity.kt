package com.abhiram.minions_ar

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.Anchor
import com.google.ar.core.AugmentedFace
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

class MainActivity : AppCompatActivity() {
    private lateinit var arFragment: ArFragment
    private var modelRenderable: ModelRenderable? = null

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
        arFragment = supportFragmentManager.findFragmentById(R.id.ux_fragment) as ArFragment
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

    }


    fun setUpMinion(){
        ModelRenderable.builder()
            .setSource(this, R.raw.minion )
            .setIsFilamentGltf(true)
            .build()
            .thenAccept {renderable: ModelRenderable ->
                modelRenderable = renderable}
            .exceptionally { throwable: Throwable? ->
                Log.i("Model", "cant load")
                Toast.makeText(this@MainActivity, "Model can't be Loaded", Toast.LENGTH_SHORT)
                    .show()
                null
            }
    }

    fun setUpM4(){
        ModelRenderable.builder()
            .setSource(this, R.raw.classic_m4 )
            .setIsFilamentGltf(true)
            .build()
            .thenAccept {renderable: ModelRenderable ->
                modelRenderable = renderable}
            .exceptionally { throwable: Throwable? ->
                Log.i("Model", "cant load")
                Toast.makeText(this@MainActivity, "Model can't be Loaded", Toast.LENGTH_SHORT)
                    .show()
                null
            }
    }

    fun setUpMustang(){
        ModelRenderable.builder()
            .setSource(this, R.raw.ford_mustang_shelby_gt500 )
            .setIsFilamentGltf(true)
            .build()
            .thenAccept {renderable: ModelRenderable ->
                modelRenderable = renderable}
            .exceptionally { throwable: Throwable? ->
                Log.i("Model", "cant load")
                Toast.makeText(this@MainActivity, "Model can't be Loaded", Toast.LENGTH_SHORT)
                    .show()
                null
            }
    }
    fun setUpShelby(){
        ModelRenderable.builder()
            .setSource(this, R.raw.ford_mustang_shelby_2012 )
            .setIsFilamentGltf(true)
            .build()
            .thenAccept {renderable: ModelRenderable ->
                modelRenderable = renderable}
            .exceptionally { throwable: Throwable? ->
                Log.i("Model", "cant load")
                Toast.makeText(this@MainActivity, "Model can't be Loaded", Toast.LENGTH_SHORT)
                    .show()
                null
            }
    }

    fun setUpInvento(){
        ModelRenderable.builder()
            .setSource(this, R.raw.invento )
            .setIsFilamentGltf(true)
            .build()
            .thenAccept {renderable: ModelRenderable ->
                modelRenderable = renderable}
            .exceptionally { throwable: Throwable? ->
                Log.i("Model", "cant load")
                Toast.makeText(this@MainActivity, "Model can't be Loaded", Toast.LENGTH_SHORT)
                    .show()
                null
            }
    }

    fun setUpGun(){
        ModelRenderable.builder()
            .setSource(this, R.raw.gun_edited)
            .setIsFilamentGltf(true)
            .build()
            .thenAccept {renderable: ModelRenderable ->
                modelRenderable = renderable}
            .exceptionally { throwable: Throwable? ->
                Log.i("Model", "cant load")
                Toast.makeText(this@MainActivity, "Model can't be Loaded", Toast.LENGTH_SHORT)
                    .show()
                null
            }
    }

    private fun setUpPlane() {
        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val anchor: Anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(arFragment.arSceneView.scene)
            createModel(anchorNode)
        }
    }

    private fun createModel(anchorNode: AnchorNode) {
        val node = TransformableNode(arFragment.transformationSystem)
        node.setParent(anchorNode)
        node.renderable = modelRenderable
        node.select()
    }
}