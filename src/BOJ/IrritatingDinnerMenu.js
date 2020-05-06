const getGcd = (a, b) => {
    if(b === 0) {
        return a;
    }else {
        return getGcd(b, a%b);
    }
}

const getFractionalFraction = (denominator, numerator) => {
    const gcd = getGcd(denominator, numerator);
    denominator = denominator / gcd;
    numerator = numerator / gcd;
    return `${numerator}/${denominator}`;
}

const initArr = (size, value) => {
    const arr = [];
    for(let i = 0; i < size; i++) {
        arr.push(value);
    }
    return arr;
}

const getPi = (key) => {
    let compare = 0;
    const length = key.length;
    let pi = initArr(length, 0);

    for(let i = 1; i < length; i++) {
        while(compare > 0 && key[i] != key[compare]) {
            compare = pi[compare-1];
        }
        if(key[i] === key[compare]) {
            pi[i] = ++compare;
        }
    }
    return pi;
}

const kmp = (context, keyword) => {
    const pi = getPi(keyword);
    const contextLength = context.length;
    const keyLength = keyword.length;
    let keyIndex = 0;
    let count = 0;

    for(let i = 0; i < contextLength; i++) {
        while (keyIndex > 0 && context[i] != keyword[keyIndex]) {
            keyIndex = pi[keyIndex - 1];
        }
        if(context[i] === keyword[keyIndex]) {
            if(keyIndex === keyLength - 1) {
                count++;
                keyIndex = pi[keyIndex];
            }else {
                keyIndex++;
            }
        }
    }
    return count;
}

const expandStr = (str) => {
    const result = str.concat(str);
    result.pop();
    return result;
}

const getProbability = (N, targetStr, rouletteStr) => {
    const target = targetStr.split(" ");
    const roulette = rouletteStr.split(" ");
    const expanded = expandStr(roulette);

    const searchCount = kmp(expanded, target);
    const length = target.length;
    const result = getFractionalFraction(length, searchCount);
    return result;
}
const target = "I W A N T M E A T";
const roulette = "E A T I W A N T M";
const result = getProbability(9, target, roulette);
console.log(result);