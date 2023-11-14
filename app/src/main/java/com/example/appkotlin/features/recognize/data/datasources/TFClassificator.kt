package com.example.appkotlin.features.recognize.data.datasources

import android.content.Context
import android.util.Log
import com.example.appkotlin.features.recognize.domain.entities.InputImage
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.Tensor
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.channels.FileChannel

class TFClassificator constructor(private val context: Context): IRecognizeService {

    private val modelFileName: String = "model.tflite";
    private val labelsFileName: String = "labels.txt";

    private lateinit var interpreter: Interpreter;
    private lateinit var labels: ArrayList<String>;

    private lateinit var inputTensor: Tensor;
    private lateinit var outputTensor: Tensor;

    private fun loadModel(){
        val modelFileDescriptor = context.assets.openFd(modelFileName);
        val modelFileChannel = FileInputStream(modelFileDescriptor.fileDescriptor).channel;
        val modelByteBuffer = modelFileChannel.map(
            FileChannel.MapMode.READ_ONLY,
            modelFileDescriptor.startOffset,
            modelFileDescriptor.declaredLength
        );

        interpreter = Interpreter(modelByteBuffer);
        modelFileDescriptor.close();

        inputTensor = interpreter.getInputTensor(0);
        outputTensor = interpreter.getOutputTensor(0);

        Log.i("open", "model");
    }

    private fun loadLabels(){
        val inputStream = context.assets.open(labelsFileName);
        val bufferedReader = BufferedReader(InputStreamReader(inputStream));
        labels = ArrayList();
        var line = bufferedReader.readLine();
        while(line != null){
            labels.add(line);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        Log.i("open", "labels");
    }

    override fun open() {
        loadLabels();
        loadModel();
    }

    override fun close() {
        interpreter.close();
        Log.i("close", "interpreter");
    }

    override fun getWidth(): Int {
        return inputTensor.shape()[1];
    }

    override fun getHeight(): Int {
        return inputTensor.shape()[2];
    }

    override fun recognize(inputImage: InputImage): Map<String, Double> {
        val inputBuffer = inputImage.byteBuffer;
        val outputBuffer = createOutputTensorBuffer();

        interpreter.run(inputBuffer, outputBuffer.buffer);

        val valueLabels = outputBuffer.floatArray;

        val maxScore = valueLabels.reduce { a, b -> a + b }
        val classification = LinkedHashMap<String, Double>();
        for (i in valueLabels.indices){
            if(valueLabels[i] > 0){
                classification[labels[i]] = valueLabels[i].toDouble() / maxScore;
            }
        }
        return classification;
    }

    private fun createOutputTensorBuffer(): TensorBuffer {
        return TensorBuffer.createFixedSize(outputTensor.shape(), outputTensor.dataType());
    }
}