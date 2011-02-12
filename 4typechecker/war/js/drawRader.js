//
// filename: drawRader.js
//
// Draw rader chart for MEME checker.
// Need the browsers which can parse HTML5/canvas.
//
// Copyright Otaking-Ex, Japan 2010. All right reserved.
//
function drawRader(top, bottom, left, right, kingPer, soldPer, schlPer, crftPer){
        var canvas = document.getElementById('rader');
	if (canvas.getContext){
		var ctx = canvas.getContext('2d');
//		ctx.beginPath();
		ctx.strokeStyle='black';
		ctx.fillRect(0,0,400,400);
		ctx.strokeStyle='rgb(91, 91, 91)';
		ctx.globalAlpha = 0.7;
		ctx.strokeRect(0, 0,50, 50);
		ctx.strokeRect(0, 50,50, 50);
		ctx.strokeRect(0, 100,50, 50);
		ctx.strokeRect(0, 150,50, 50);
		ctx.strokeRect(0, 200,50, 50);
		ctx.strokeRect(0, 250,50, 50);
		ctx.strokeRect(0, 300,50, 50);
		ctx.strokeRect(0, 350,50, 50);
		ctx.strokeRect(50, 0,50, 50);
		ctx.strokeRect(50, 50,50, 50);
		ctx.strokeRect(50, 100,50, 50);
		ctx.strokeRect(50, 150,50, 50);
		ctx.strokeRect(50, 200,50, 50);
		ctx.strokeRect(50, 250,50, 50);
		ctx.strokeRect(50, 300,50, 50);
		ctx.strokeRect(50, 350,50, 50);
		ctx.strokeRect(100, 0,50, 50);
		ctx.strokeRect(100, 50,50, 50);
		ctx.strokeRect(100, 100,50, 50);
		ctx.strokeRect(100, 150,50, 50);
		ctx.strokeRect(100, 200,50, 50);
		ctx.strokeRect(100, 250,50, 50);
		ctx.strokeRect(100, 300,50, 50);
		ctx.strokeRect(100, 350,50, 50);
		ctx.strokeRect(150, 0,50, 50);
		ctx.strokeRect(150, 50,50, 50);
		ctx.strokeRect(150, 100,50, 50);
		ctx.strokeRect(150, 150,50, 50);
		ctx.strokeRect(150, 200,50, 50);
		ctx.strokeRect(150, 250,50, 50);
		ctx.strokeRect(150, 300,50, 50);
		ctx.strokeRect(150, 350,50, 50);
		ctx.strokeRect(200, 0,50, 50);
		ctx.strokeRect(200, 50,50, 50);
		ctx.strokeRect(200, 100,50, 50);
		ctx.strokeRect(200, 150,50, 50);
		ctx.strokeRect(200, 200,50, 50);
		ctx.strokeRect(200, 250,50, 50);
		ctx.strokeRect(200, 300,50, 50);
		ctx.strokeRect(200, 350,50, 50);
		ctx.strokeRect(250, 0,50, 50);
		ctx.strokeRect(250, 50,50, 50);
		ctx.strokeRect(250, 100,50, 50);
		ctx.strokeRect(250, 150,50, 50);
		ctx.strokeRect(250, 200,50, 50);
		ctx.strokeRect(250, 250,50, 50);
		ctx.strokeRect(250, 300,50, 50);
		ctx.strokeRect(250, 350,50, 50);
		ctx.strokeRect(300, 0,50, 50);
		ctx.strokeRect(300, 50,50, 50);
		ctx.strokeRect(300, 100,50, 50);
		ctx.strokeRect(300, 150,50, 50);
		ctx.strokeRect(300, 200,50, 50);
		ctx.strokeRect(300, 250,50, 50);
		ctx.strokeRect(300, 300,50, 50);
		ctx.strokeRect(300, 350,50, 50);
		ctx.strokeRect(350, 0,50, 50);
		ctx.strokeRect(350, 50,50, 50);
		ctx.strokeRect(350, 100,50, 50);
		ctx.strokeRect(350, 150,50, 50);
		ctx.strokeRect(350, 200,50, 50);
		ctx.strokeRect(350, 250,50, 50);
		ctx.strokeRect(350, 300,50, 50);
		ctx.strokeRect(350, 350,50, 50);
		ctx.beginPath();
//		ctx.moveTo(200, 250);
		ctx.globalAlpha = 0.4;
//		ctx.strokeStyle = 'rgb(c5, c5, c5)';
//
//		ctx.fill();
//		ctx.moveTo(200, 300);
		ctx.arc(200, 200, 100, 0, Math.PI*2, true);
//		ctx.moveTo(200, 350);
		ctx.arc(200, 200, 150, 0, Math.PI*2, true);
//		ctx.moveTo(200, 400);
		ctx.arc(200, 200, 200, 0, Math.PI*2, true);
		ctx.stroke();
//		ctx.endPath();
//		ctx.beginPath();
		var grad  = ctx.createRadialGradient(200,200,50,200,200,80);
		grad.addColorStop(0,'white');
		grad.addColorStop(0.2,'rgb(91, 91, 91)');
		grad.addColorStop(1,'black');
		ctx.fillStyle=grad;
		ctx.arc(200, 200, 50, 0, Math.PI*2, true);
		ctx.fill();
//		ctx.stroke();
		ctx.beginPath();
		ctx.globalAlpha = 1;
		ctx.strokeStyle = 'white';
		ctx.moveTo(200,0);
		ctx.lineTo(200, 400);
		ctx.moveTo(0, 200);
		ctx.lineTo(400, 200);
		ctx.stroke();
		ctx.globalAlpha = 1;

		var x = 200;
		var y = 200;

		var ydiff = top - bottom;
		if(ydiff>=0){
			y = 200 - (5*ydiff)
		}else{
			y = 200 - (5*ydiff);
		}

		var xdiff = right - left;
		if(xdiff>=0){
			x = 200 + 8*xdiff;
		}else{
			x = 200 + 8*xdiff;
		}

//		var img = new Image();
//		img.src = "images/okada.jpg?" + new Date().getTime();
//		img.onload = function() {
//			ctx.drawImage(img, 300-29, 100-36);
//
//		}

		var img = new Image();
////		img.src = imagePath + "?" + new Date().getTime();
		img.src = "images/neko02b.gif?" + new Date().getTime();
//		img.src = "images/uboat.gif?" + new Date().getTime();
//		img.src = "images/tama.gif?" + new Date().getTime();
		img.onload = function() {
			ctx.drawImage(img, x-20, y-25);
//			ctx.drawImage(img, x-15, y-25);
		}
		ctx.beginPath();
		ctx.font = "18px 'ＭＳ　Ｐゴシック'";
		ctx.strokeStyle = 'white';
//		ctx.strokeText("司令型(" + soldPer + "%)", 300,  18);
//		ctx.strokeText("注目型(" + kingPer + "%)",   0,  18);
//		ctx.strokeText("理想型(" + crftPer + "%)",   0, 395);
//		ctx.strokeText("法則型(" + schlPer + "%)", 300, 395);
		ctx.strokeText("司令型", 300,  18);
		ctx.strokeText("注目型",   0,  18);
		ctx.strokeText("理想型",   0, 395);
		ctx.strokeText("法則型", 300, 395);
		ctx.stroke();
		ctx.beginPath();
		ctx.font = "9px 'ＭＳ　Ｐゴシック'";
		ctx.strokeStyle = 'white';
		ctx.strokeText("-24", 　　　0, 200);
		ctx.strokeText(　"40", 200, 　　　8);
		ctx.strokeText("-40", 200, 400);
		ctx.strokeText(　"24", 390, 200);
		ctx.strokeText(　　"0", 200, 200);
		ctx.stroke();

	}

}