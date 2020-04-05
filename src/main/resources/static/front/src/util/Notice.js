import axios from 'axios'
import {getAccessToken} from "./User";

const newPath = "/api/notice/new"

export const getNewNoticeNum = async () => {
    const token = getAccessToken()
    const headers = {
        "Authorization": "Bearer " + token
    }

    const result = (await axios.get(newPath, {
        headers: headers
    })).data

    console.log(result)

    return result
}

