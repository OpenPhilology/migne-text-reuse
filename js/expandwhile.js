function expandWhile(test,array,index) {
    var res = [index];
    var i = index-1;
    while (i>-1 && test(array[i])) {
        res.push(i);
	i = i-1;
    }
    i = index+1;
    array = array.reverse;
    while (i<array.length && test(array[i])) {
        res.push(i);
        i = i+1;
    }
    return array;
}
