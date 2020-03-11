export const convertOfferTypeString = (offerType) => {

    console.log(offerType)
    switch (offerType) {
        case 'Email 제공':
            return "Email";
            break;
        case '유선통화':
            return "전화";
            break;
        case '오프라인 인터뷰':
            return "인터뷰";
            break;
    }
}