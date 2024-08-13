import {Button, Grid} from "@mui/material";
import React from "react";

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

