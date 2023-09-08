//getting html elements to modify
var pupil1HtmlCollection = document.getElementsByClassName("pupil1");
var pupil2HtmlCollection = document.getElementsByClassName("pupil2");
var pupil1Array = Array.from(pupil1HtmlCollection);
var pupil2Array = Array.from(pupil2HtmlCollection);

//input for the eyes
var input = {
    mouseX: {
        start: 0,
        end: window.innerWidth,
        current: 0, 
    },
    mouseY: { start: 0,
        end: window.innerHeight,
        current: 0, },
};
var output = {
    x: {
        start: -75,
        end: 75,
        current: 0,

    },
    y:{start: -75,
        end: 75,
        current: 0},
};
//output for the eyes
output.x.range = output.x.end - output.x.start;
output.y.range = output.y.end - output.y.start;

input.mouseX.range = input.mouseX.end - input.mouseX.start;
input.mouseY.range = input.mouseY.end - input.mouseY.start;
//getting the input from mouse
var handleMouseMove = function(event) {
    input.mouseX.current= event.clientX;
    input.mouseX.fraction = (input.mouseX.current - input.mouseX.start)
    /input.mouseX.range;
    input.mouseY.current= event.clientY;
    input.mouseY.fraction = (input.mouseY.current - input.mouseY.start)
    /input.mouseY.range;
    output.x.current = output.x.start + 
    (input.mouseX.fraction * output.x.range)
    output.y.current = output.y.start + 
    (input.mouseY.fraction * output.y.range)
//apply to html
pupil1Array.forEach(function(pupil1, k) {
    pupil1.style.transform = `translateX(${output.x.current}px) 
    translateY(${output.y.current}px)`;
 
});

pupil2Array.forEach(function(pupil2, k) {
    pupil2.style.transform = `translateX(${-output.x.current}px) 
    translateY(${-output.y.current}px)`;
 
});

};
//resizing accoring to screen
var handleResize = function(event) {
    input.mouseX.end = window.innerWidth;
    input.mouseX.range = input.mouseX.end - input.mouseX.start;
    input.mouseY.end = window.innerHeight;
    input.mouseY.range = input.mouseY.end - input.mouseY.start;
}
window.addEventListener("mousemove", handleMouseMove);
window.addEventListener("mousemove", handleResize)
