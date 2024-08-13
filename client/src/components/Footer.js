import React from 'react';
import "./css/Footer.css";
import Typography from "@mui/material/Typography";
import Link from "@mui/material/Link";

const Footer = (props)=> {
    return (
        <Typography variant="body2" color="text.secondary" align="center" {...props}>
            {'Copyright © '}
                EZBid {' '}
            {new Date().getFullYear()}
        </Typography>
    );
};

export default Footer;
