import axios from "axios";

// Define the API path here
export class ApiMethod {
    static ALL_AUCTIONS = "auctions/all";
    static MY_BIDS = "bids/mybids";
    static PLACE_BID = "bids/place";
    static PROFILE = "users/profile";
    static EDIT_PROFILE = "users/editprofile";
    static NEW_AUCTION = "auctions/new";
    static DELETE_AUCTION = "auctions/";
    static ALL_CATEGORIES = "categories/all";
}

// Define the API get call here
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

// Define the API post call here
export async function postApiCalls(apiPath, formData) {
    const headers = {
        'Authorization': `Bearer ${localStorage.token}`,
        // No need to set Content-Type, Axios will handle it for FormData
    };

    try {
        const response = await axios.post(`http://localhost:8080/api/${apiPath}`, formData, { headers });
        return response.data;
    } catch (error) {
        console.error('API call error:', error);
        throw error;
    }
}

// Define the API delete call here
export async function deleteApiCalls(apiPath) {
    const headers = {};
    headers['Authorization'] = `Bearer ${localStorage.token}`;
    try {
        const response = await axios.delete(`http://localhost:8080/api/${apiPath}`, { headers });
        return response.data;
    } catch (error) {
        console.error('API call error:', error);
        throw error;
    }
}


export default makeApiCall;



