function counter() {
    let count = 0;
    return function getCount() {
        count++;
        return count;
    }
}

let increment = counter();

let count1 = increment();
let count2 = increment();
let count3 = increment();

console.log(count1 + ',' + count2 + ',' + count3);

function counter2() {
    let count = 0;
    return {
        //'count':0,
        'setCount': (x) => {count = x},
        'getCount': () => {return count}
    }
}

let obj = counter2();
let x = obj.getCount();
obj.setCount(x+1);
console.log(obj.getCount());