function test(array,index,flag) {
    return (array[index].gt==array[index].ocr)==flag;
}

function expandWhile(test,array,index) {
    var b = array[index].gt==array[index].ocr
    var res = [index];
    var i = index-1;
    while (i>-1 && test(array,i,b)) {
        res.push(i);
	i = i-1;
    }
    i = index+1;
    res = res.reverse();
    while (i<array.length && test(array,i,b)) {
        res.push(i);
        i = i+1;
    }
    return res;
}

function map(array,range) {return range.map(function(i) {return array[i];});}

function split(array) {
    var res1 = array.filter(function(d){return d.ocr!="null"}).map(function(d){return d.ocr});
    var res2 = array.filter(function(d){return d.gt!="null"}).map(function(d){return d.gt});
    return {ocr:res1,gt:res2}
}

function display(obj) {
    var res = "";
    if (obj && obj.ocr && obj.gt) {
        if (obj.ocr.head==obj.gt.head) res = obj.ocr.join(" ")
        else res = obj.ocr.join(" ")+"<b>"+obj.gt.join(" ")+"</b>"
    }
    return res;
}