import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";

const fields = [
    { id: 'firstName', label: 'First Name' },
    { id: 'lastName', label: 'Last Name' },
    { id: 'dob', label: 'Date of Birth' },
    { id: 'dateOfJoin', label: 'Date of Join' },
    { id: 'country', label: 'Country' },
    { id: 'fullAddress', label: 'Full Address' },
    { id: 'zipCode', label: 'Zip Code' },
    { id: 'email', label: 'Email Address' },
    { id: 'phoneNumber', label: 'Phone Number' },
];

const ProfileEditComponents = () => {
    return (
        <Grid container spacing={2}>
            {fields.map((field) => (
                <Grid item xs={12} key={field.id}>
                    <TextField
                        id={field.id}
                        label={field.label}
                        variant="outlined"
                        fullWidth
                        InputProps={{
                            sx: {
                                color: 'black', // Change input text color
                                '& .MuiOutlinedInput-notchedOutline': {
                                    borderColor: 'black', // Change border color
                                },
                                '&:hover .MuiOutlinedInput-notchedOutline': {
                                    borderColor: 'darkgreen', // Change border color on hover
                                },
                                '&.Mui-focused .MuiOutlinedInput-notchedOutline': {
                                    borderColor: 'purple', // Change border color when focused
                                },

                            },
                        }}
                        InputLabelProps={{
                            sx: {
                                color: 'black', // Change label color
                                '&.Mui-focused': {
                                    color: 'purple', // Change label color when focused
                                },
                            },
                        }}
                    />
                </Grid>
            ))}
        </Grid>
    );
};

export default ProfileEditComponents;
