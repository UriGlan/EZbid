import * as React from 'react';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import Box from '@mui/material/Box';
import { createTheme, ThemeProvider } from '@mui/material/styles';


const theme = createTheme({
    palette: {
        customPrimary: {
            main: '#000000', // Custom color
        },
        customSecondary: {
            main: '#ffffff', // Custom color
        },
    },
});

export default function VariantButtonGroup() {
    return (
        <ThemeProvider theme={theme}>
        <Box
            sx={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                '& > *': {
                    m: 1,
                },
                backgroundColor: (t) =>
                    t.palette.mode === 'dark' ? t.palette.grey[50] : t.palette.grey[900],
                backgroundSize: 'auto',
            }}
        >
            <ButtonGroup color="customSecondary" variant="text" aria-label="Basic button group">
                <Button>Home</Button>
                <Button>My Account</Button>
                <Button>Logout</Button>
            </ButtonGroup>
        </Box>
        </ThemeProvider>
    );
}
