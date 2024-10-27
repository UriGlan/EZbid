import {AppBar, Toolbar} from "@mui/material";
import logo from "../../img/logo.png";
import Box from "@mui/material/Box";
import VariantButtonGroup from "../VariantButtonGroup";
import React from "react";

// AppBar with logo and button group
const VariantAppBar = () =>{
    return(
        <AppBar position="fixed"
                sx={{
                    backgroundColor: (t) =>
                        t.palette.mode === 'dark' ? t.palette.grey[50] : t.palette.grey[900],
                    backgroundSize: 'auto',
                }}>
            <Toolbar>
                <img src={logo} alt="Logo" style={{ height: '70px', marginRight: '10px' }} />
                <Box sx={{ position: 'absolute', right: 0 }}>
                    <VariantButtonGroup />
                </Box>
            </Toolbar>
        </AppBar>
    )
};

export default VariantAppBar;