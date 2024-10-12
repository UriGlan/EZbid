import * as React from 'react';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import CssBaseline from '@mui/material/CssBaseline';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import VariantAppBar from "../components/menu/VariantAppBar";
import {AccountCircle} from "@mui/icons-material";
import GavelIcon from '@mui/icons-material/Gavel';
import AccountBalanceWalletIcon from '@mui/icons-material/AccountBalanceWallet';
import {useNavigate} from "react-router-dom";

const drawerWidth = 240;


const PermanentDrawerLeft = () => {
    const navigate = useNavigate();

    const drawerItems = [
        { text: 'My Bids', icon: <GavelIcon />, onClick:() => navigate('/mybids') },
        { text: 'My Listed Items', icon: <AccountBalanceWalletIcon />, onClick: () => navigate('/myitems') },
        { text: 'Profile', icon: <AccountCircle />, onClick: () => navigate('/profile') },
    ];
    return (
        <>
            <VariantAppBar/>
            <Box sx={{ display: 'flex', marginTop:'70px' }}>
                <CssBaseline />
                <Drawer
                    sx={{
                        width: drawerWidth,
                        flexShrink: 0,
                        '& .MuiDrawer-paper': {
                            width: drawerWidth,
                            boxSizing: 'border-box',
                            top: 'auto',
                        },
                    }}
                    variant="permanent"
                    anchor="left"
                >
                    <List>
                        {drawerItems.map((item, index) => (
                            <ListItem key={item.text} disablePadding>
                                <ListItemButton onClick={item.onClick}>
                                    <ListItemIcon>
                                        {item.icon}
                                    </ListItemIcon>
                                    <ListItemText primary={item.text} />
                                </ListItemButton>
                            </ListItem>
                        ))}
                    </List>
                    <Divider />
                </Drawer>

            </Box>
        </>
    );
}

export default PermanentDrawerLeft