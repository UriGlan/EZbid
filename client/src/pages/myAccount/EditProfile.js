import VariantAppBar from "../../components/menu/VariantAppBar";
import Footer from "../../components/Footer";
import PermanentDrawerLeft from "../../components/PermanentDrawerLeft";
import Typography from "@mui/material/Typography";
import {Box, Container} from "@mui/material";
import Button from "@mui/material/Button";
import * as React from "react";
import ProfileEditComponents from "../../components/ProfileEditComponents";
import {useNavigate} from "react-router-dom";
import makeApiCall, {ApiMethod, postApiCalls} from "../../Utils/ApiUtils";
import {useEffect} from "react";


// Edit Profile Page
const EditProfile = () => {
    const navigate = useNavigate();
    const [profile, setProfile] = React.useState({});
    const [error, setError] = React.useState('');

    const fetchProfile = async () => {
        try {
            const data = await makeApiCall(ApiMethod.PROFILE)
            setProfile(data);

        } catch (error) {
            console.error('Error fetching profile:', error);
        }
    };

    // Fetch Profile
    useEffect(() => {
        fetchProfile()
    }, []);

    // Save Profile Changes
    const saveProfileChanges = async () => {
        try {
            await postApiCalls(ApiMethod.EDIT_PROFILE, {firstName: profile.firstName, lastName: profile.lastName});
            navigate('/profile');
        } catch (error) {
            console.error('Error updaing profile:', error);
            const errorMessage = error.response.data.message;
            setError(errorMessage || 'Update failed.');
        }
    }

    return (
        <>
            <VariantAppBar/>
            <PermanentDrawerLeft/>
            <main>
                <Box
                    sx={{
                        height: 'auto',
                        paddingLeft:35,
                        paddingRight:7,
                        paddingTop: '80px',
                        paddingBottom: '15%'
                    }}>
                    <Typography
                        variant='h2'
                        align='center'
                        paddingTop={2}
                        fontWeight={700}
                    >
                        Profile Edit
                    </Typography>
                    <Container    sx={{
                        paddingTop: 7,
                        paddingBottom: 17,
                        display: 'flex',
                        justifyContent: 'center', // Center the Divider horizontally
                        alignItems: 'center',
                        flexDirection: 'column',
                    }} >
                        <Box sx={{textAlign: 'center' , maxWidth:'70%'}}>
                            <ProfileEditComponents profile={profile} onChange={(id, value) => setProfile({ ...profile, [id]: value })} />
                        </Box>
                    </Container>
                    {error && <Typography variant='h6' color='error' align='center'>{error}</Typography>}
                    <Box sx={{ textAlign: 'center', paddingBottom:7 }}>
                        <Button  variant="contained" color="primary" sx={{
                            backgroundColor: '#748eeb',
                            width: '7em',
                            '&:hover': {
                                backgroundColor: '#5a7ddb', // Change this to your desired hover color
                            }
                        }} onClick={saveProfileChanges}>
                            Save
                        </Button>
                        <Button  variant="contained" color="primary" sx={{
                            marginLeft:3,
                            backgroundColor: '#748eeb',
                            width: '7em',
                            '&:hover': {
                                backgroundColor: '#5a7ddb', // Change this to your desired hover color
                            }}} onClick={()=>navigate('/profile')}>
                            Cancel
                        </Button>
                    </Box>
                    <Footer/>
                </Box>
            </main>
        </>
    )
};
export default EditProfile;