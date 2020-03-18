import jwt_decode from 'jwt-decode'

const accessTokenVar = 'expAccessToken'

export function getAccessToken() {
    const token = localStorage.getItem(accessTokenVar)

    return token
}

export function removeAccessToken() {
    localStorage.removeItem(accessTokenVar)
}

export function getDecodedToken() {
    const token = getAccessToken()

    if (token) {
        return jwt_decode(token)
    } else {
        return ''
    }
}

export function getEmail() {
    const token = getDecodedToken()

    return token.email
}

export function getNickname() {
    const token = getDecodedToken()

    return token.nickname
}

export function getAuth() {
    return getDecodedToken().Authorities
}
