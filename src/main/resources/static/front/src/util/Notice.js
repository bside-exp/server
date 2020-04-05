import axios from 'axios'
import {getAccessToken} from "./User";

const newPath = "/api/notice/new"
const noticesPath = "/api/notice"
const readNoticePath = "/api/notice/read"

export const getNewNoticeNum = async () => {
    const token = getAccessToken()
    const headers = {
        "Authorization": "Bearer " + token
    }

    const result = (await axios.get(newPath, {
        headers: headers
    })).data

    return result
}

export const getNotices = async () => {
    const token = getAccessToken()
    const headers = {
        "Authorization": "Bearer " + token
    }

    const url = noticesPath + '?page=1'

    const result = (await axios.get(url, {
        headers: headers
    })).data.content

    return result
}

export const readNotices = async () => {
    const token = getAccessToken()
    const headers = {
        "Authorization": "Bearer " + token
    }

    const result = (await axios.post(readNoticePath, {}, {
        headers: headers
    })).data

    return result
}
