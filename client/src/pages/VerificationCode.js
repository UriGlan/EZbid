import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Footer from "../components/Footer";
import logo from "../img/logo.png";
import axios from "axios";
import { useState } from "react";

const defaultTheme = createTheme();

const VerificationCode = () => {
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);

        const resetData = {
            email: data.get('email'),
            verificationCode: data.get('verificationCode'),  // Assuming you have a token in the reset email
        };

        try {
            // Send a POST request to the backend for resetting the password
            const response = await axios.post('http://localhost:8080/auth/verify', {
                email: resetData.email,
                verificationCode: resetData.verificationCode,
            });
            setMessage('Verification successful. Redirecting to sign in page...');
            setTimeout(() => {window.location.href = '/signIn'}, 2500 );
        } catch (error) {
            if (error.response && error.response.data) {
                setError(error.response.data.message || 'Verification failed.');
            } else {
                setError('Verification failed. Please try again.');
            }
            console.error('There was an error!', error);
        }
    };

    return (
        <ThemeProvider theme={defaultTheme}>
            <Grid container component="main" sx={{ height: '100vh' }}>
                <CssBaseline />
                <Grid
                    item
                    xs={false}
                    sm={4}
                    md={7}
                    sx={{
                        backgroundImage: `url(${logo})`,
                        backgroundColor: (t) =>
                            t.palette.mode === 'dark' ? t.palette.grey[50] : t.palette.grey[900],
                        backgroundSize: 'auto',
                        backgroundRepeat: 'no-repeat',
                        backgroundPosition: 'left',
                    }}
                />
                <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
                    <Box
                        sx={{
                            my: 8,
                            mx: 4,
                            display: 'flex',
                            flexDirection: 'column',
                            alignItems: 'center',
                        }}
                    >
                        <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                            <LockOutlinedIcon />
                        </Avatar>
                        <Typography component="h1" variant="h5">
                            Verify Account
                        </Typography>
                        <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                id="email"
                                label="Email Address"
                                name="email"
                                autoComplete="email"
                                autoFocus
                            />
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                id="verificationCode"
                                label="Verification code"
                                name="verificationCode"
                                autoComplete="verificationCode"
                            />
                            <Box>
                                {error && <Typography color="error" variant="body2" sx={{ mt: 2}}>{error}</Typography>}
                                {message && <Typography color="success" variant="body2" sx={{ mt: 2}}>{message}</Typography>}
                            </Box>

                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                sx={{ mt: 3, mb: 2 }}
                            >
                                Verify Account
                            </Button>
                            <Footer sx={{ mt: 5 }} />
                        </Box>
                    </Box>
                </Grid>
            </Grid>
        </ThemeProvider>
    );
}

export default VerificationCode;
