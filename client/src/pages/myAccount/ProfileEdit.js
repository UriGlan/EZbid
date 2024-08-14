import VariantAppBar from "../../components/menu/VariantAppBar";
import Footer from "../../components/Footer";
import PermanentDrawerLeft from "../../components/PermanentDrawerLeft";
import Typography from "@mui/material/Typography";
import {Box, Container} from "@mui/material";
import DividerVariants from "../../components/DividerVariants";
import Button from "@mui/material/Button";
import * as React from "react";
import ProfileEditComponents from "../../components/ProfileEditComponents";

const EditProfile = () => {
    return (
        <>
            <VariantAppBar/>
            <PermanentDrawerLeft/>
            <main>
                <Box
                    sx={{
                        backgroundColor: '#d7d7d7',
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
                            <ProfileEditComponents />
                        </Box>
                    </Container>
                    <Box sx={{ textAlign: 'center', paddingBottom:7 }}>
                        <Button  variant="contained" color="primary" sx={{width: 5}}>
                            Save
                        </Button>
                        <Button  variant="contained" color="primary" sx={{marginLeft:3, width: 5}}>
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