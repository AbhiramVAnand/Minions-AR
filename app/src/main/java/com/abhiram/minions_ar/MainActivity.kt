package com.abhiram.minions_ar

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import java.util.function.Consumer
import java.util.function.Function


class MainActivity : AppCompatActivity() {
    private val arFragment: ArFragment = null
    private var modelRenderable: ModelRenderable? = null

    //3d model credit : google.poly.com
    private val Model_URL = "https://modelviewer.dev/shared-assets/models/Astronaut.glb"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arFragment = supportFragmentManager.findFragmentById(R.id.ux_fragment)
    }

    private fun setUpModel() {
        ModelRenderable.builder()
            .setSource(
                this,
                RenderableSource.builder().setSource(
                    this,
                    Uri.parse(Model_URL),
                    RenderableSource.SourceType.GLB
                )
                    .setScale(0.75f)
                    .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                    .build()
            )
            .setRegistryId(Model_URL)
            .build()
            .thenAccept(Consumer<ModelRenderable> { renderable: ModelRenderable ->
                modelRenderable = renderable
            })
            .exceptionally(Function<Throwable, Void?> { throwable: Throwable? ->
                Log.i("Model", "cant load")
                Toast.makeText(this@MainActivity, "Model can't be Loaded", Toast.LENGTH_SHORT)
                    .show()
                null
            })
    }

    private fun setUpPlane() {
        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val anchor: Anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(arFragment.getArSceneView().getScene())
            createModel(anchorNode)
        }
    }

    private fun createModel(anchorNode: AnchorNode) {
        val node = TransformableNode(arFragment.getTransformationSystem())
        node.setParent(anchorNode)
        node.renderable = modelRenderable
        node.select()
    }
}