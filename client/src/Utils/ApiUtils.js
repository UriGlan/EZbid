import axios from "axios";

export class ApiMethod {
    static ALL_AUCTIONS = "auctions/all";
    static MY_BIDS = "bids/mybids";
    static PLACE_BID = "bids/place";
    static PROFILE = "users/profile";
    static EDIT_PROFILE = "users/editprofile";
}

async function makeApiCall (apiPath){
    const headers = {};
    headers['Authorization'] = `Bearer ${localStorage.token}`;
    try {
        const response = await axios.get(`http://localhost:8080/api/${apiPath}`, { headers });
        return response.data;
    } catch (error) {
        console.error('API call error:', error);
        throw error;
    }
}

export async function postApiCalls(apiPath, data){
    const headers = {};
    headers['Authorization'] = `Bearer ${localStorage.token}`;
    console.log(data);
    try {
        const response = await axios.post(`http://localhost:8080/api/${apiPath}`,data ,{ headers });
        return response.data;
    } catch (error) {
        console.error('API call error:', error);
        throw error;
    }
}

export default makeApiCall;



