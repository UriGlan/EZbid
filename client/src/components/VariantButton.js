import {Button, Grid} from "@mui/material";
import React from "react";

// This is a simple button that changes the color of the button when clicked
const VariantButton = () => {
    return(
        <Grid container spacing={2} justifyContent="center">
            <Grid item>
                <Button variant="contained" color="primary">
                    Change
                </Button>
            </Grid>
        </Grid>
    )};
export default VariantButton;

