const fetch = require('@system.fetch')
const prompt = require('@system.prompt')

export const http = {
    GET: function (url, param, callback) {
        fetch.fetch({
            url: url,
            method: 'GET',
            data: param,
            success: function (res) {
                console.log(res.data)
                callback(res.data)
            },
            fail: function (res, code) {
                console.log(url + ":\n" + res)
                callback(res)
            }
        })
    },

    getMethod: function (url, param, callback) {
        fetch.fetch({
            url: url,
            method: 'GET',
            data: JSON.stringify(param),
            contentType: 'application/json',
            success: function (res) {
                console.log(JSON.stringify(res.data.sendData))
                callback(res.data.sendData)
            },
            fail: function (res, code) {
                console.log(url + ":\n" + res)
                callback(res.data.sendDate)
            }
        })
    },


    postMethod: function (url, param, callback) {
        console.log(`[POST] ${url}`)
        console.log(`[PARAM]  ${JSON.stringify(param)}`)
        fetch.fetch({
            url: url,
            method: 'POST',
            header: {'Content-Type': 'application/json;charset=UTF-8'},
            data: JSON.stringify(param),
            success: function (res) {
                console.log(`[CALLBACK] ${res.data}`)
                var obj = JSON.parse(res.data)
                if (obj.e.code === 0) {
                    callback(obj.data.sendData)
                } else {
                    prompt.showToast({
                        message: obj.e.desc
                    })
                }
            },
            fail: function (res, code) {
                console.log(res + " | " + "code= " + code)
                callback(res)
            }
        })
    }
}