package utils.json

fun convert(input: dynamic) : dynamic {
    js("for(var k in input) {\n" +
            "\n" +
            "var newK = k.replace(/_kj/g, '').replace(/_/g, '').replace(/z/g, '').toLowerCase()\n" +
            "input[newK] = input[k]\n" +
            "if(k !== newK) {\n" +
            "delete input[k]\n" +
            "}\n" +
            "\n" +
            "}")

    return input
}

fun convertAll(obj: dynamic) : dynamic {
    js("\n" +
            "\n" +
            "for(var j in obj) {\n" +
            "var input = obj[j]\n" +
            "for(var k in input) {\n" +
            "\n" +
            "var newK = k.replace(/_kj/g, '').replace(/_/g, '').replace(/z/g, '').toLowerCase()\n" +
            "input[newK] = input[k]\n" +
            "if(k !== newK) {\n" +
            "delete input[k]\n" +
            "}\n" +
            "\n" +
            "}\n" +
            "obj[j] = input\n" +
            "}")

    return obj
}

fun convertAllKf(obj: dynamic) : dynamic {
    js("\n" +
            "\n" +
            "var count = Object.keys(obj).length\n" +
            "for(var j in obj) {\n" +
            "var input = obj[j]\n" +
            "for(var k in input) {\n" +
            "\n" +
            "var newK = k === 'v' ? 'vl' : 'record'\n" +
            "input[newK] = input[k]\n" +
            "if(k !== newK) {\n" +
            "delete input[k]\n" +
            "}\n" +
            "\n" +
            "}\n" +
            "input['place'] = count--\n" +
            "obj[j] = input\n" +
            "obj = require('reverse-object-order')(obj)\n" +
            "}")

    return obj
}