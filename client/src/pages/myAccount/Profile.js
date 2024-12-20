import VariantAppBar from "../../components/menu/VariantAppBar";
import Footer from "../../components/Footer";
import PermanentDrawerLeft from "../../components/PermanentDrawerLeft";
import Typography from "@mui/material/Typography";
import {Box, Container} from "@mui/material";
import DividerVariants from "../../components/DividerVariants";
import Button from "@mui/material/Button";
import * as React from "react";
import makeApiCall, {ApiMethod} from "../../Utils/ApiUtils";
import {useEffect} from "react";
import {useNavigate} from "react-router-dom";

// Profile page
const Profile = () => {
    const navigate = useNavigate();
    const [profile, setProfile] = React.useState({});
    const fetchProfile = async () => {
        try {
            const data = await makeApiCall(ApiMethod.PROFILE)
            setProfile(data);

        } catch (error) {
            console.error('Error fetching profile:', error);
        }
    };

    useEffect(() => {
        fetchProfile()
    }, []);


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
                        My Profile
                    </Typography>
                    <Container    sx={{
                        paddingTop: 7,
                        paddingBottom: 17,
                        fontWeight: 700,
                        display: 'flex',
                        justifyContent: 'center', // Center the Divider horizontally
                        alignItems: 'center', // Center the Divider vertically (if applicable)
                    }} >
                        <DividerVariants profile={profile}/>
                    </Container>
                    <Box sx={{ textAlign: 'center', paddingBottom:7 }}>
                        <Button  variant="contained" color="primary" onClick={() => navigate("/editprofile")} sx={{
                            backgroundColor: '#748eeb',
                            width: '7em',
                            '&:hover': {
                                backgroundColor: '#5a7ddb', // Change this to your desired hover color
                            }
                        }}>
                            Edit
                        </Button>
                    </Box>
                    <Footer/>
                </Box>
            </main>
        </>
    )
};
export default Profile;