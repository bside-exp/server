scroll = (func) => {
    let scrollHeight = Math.max(document.documentElement.scrollHeight, document.body.scrollHeight)
    let scrollTop = Math.max(document.documentElement.scrollTop, document.body.scrollTop)
    let clientHeight = document.documentElement.clientHeight

    if (scrollTop + clientHeight === scrollHeight) {
        func()
    }
}

export default scroll
