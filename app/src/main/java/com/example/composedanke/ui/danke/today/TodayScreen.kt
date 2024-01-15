package com.example.composedanke.ui.danke.today

import android.annotation.SuppressLint
import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun TodayScreen(
    viewModel: TodayViewModel,
) {
    Column(
        modifier = Modifier
            .background(Color.Cyan)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Today Screen")
        composeCanvasView()
        customCanvasView()
    }
}

@Composable
private fun customCanvasView() {
    val mContext = LocalContext.current
    Canvas(modifier = Modifier
        .size(400.dp)
        .padding(16.dp)
        .background(Color.Green)
    ){
        this.drawRect(
            color = Color.Red,
            size = Size(200f, 100f),
        )

        val customDrawer = ChartTest(this,mContext)
        customDrawer.drawCircle(
            color = Color.Blue,
            radius = 50f,
            center = Offset(300f, 200f)
        )
    }
}

@SuppressLint("RememberReturnType")
@Composable
private fun composeCanvasView() {
    val animatedValue = remember { 0f }
    var percent: Float = 10f
    val scoreTextPaint = TextPaint().apply {
        textAlign = android.graphics.Paint.Align.CENTER
        textSize = 50f
        color = Color.Black.toArgb()
    }

//Canvas(modifier = Modifier.fillMaxWidth().height(200.dp), onDraw = )
    Column(modifier = Modifier.background(Color.Yellow)) {
        Box(
            modifier = Modifier.size(400.dp, 200.dp)
        ) {
            Canvas(modifier = Modifier
                .size(400.dp)
                .padding(16.dp)
            ) {
                drawArc(
                    color = Color.LightGray,
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    size = Size(900.dp.value, 900.dp.value),
                    style = Stroke(width = 40f, cap = StrokeCap.Round)
                )

                drawArc(
                    color = Color.Green,
                    startAngle = 180f,
                    sweepAngle = 10f,
                    useCenter = false,
                    size = Size(900.dp.value, 900.dp.value),
                    style = Stroke(width = 40f, cap = StrokeCap.Round)
                )

                drawContext.canvas.nativeCanvas.drawText(percent.toString(), center.x, 200f, scoreTextPaint)
            }
        }
    }
}
