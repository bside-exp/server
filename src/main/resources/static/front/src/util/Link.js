import axios from 'axios'

const OFFER_PATH = "/link/offer"
const REQUEST_PATH = "/link/request"

export const getLinkByOfferAndRequester = async (offerId, requesterToken) => {
    const url = OFFER_PATH + "?offerId=" + offerId;
    const headers = {
        "Authorization": "Bearer " + requesterToken
    }

    const result = (await axios.get(url, {
        headers: headers
    })).data

    return result
};

export const getLinkByRequestAndProvider = async (requestId, providerToken) => {
    const url = REQUEST_PATH + "?requestId=" + requestId
    const headers = {
        "Authorization": "Bearer " + providerToken
    }

    const result = (await axios.get(url, {
        headers: headers
    })).data

    return result
}
