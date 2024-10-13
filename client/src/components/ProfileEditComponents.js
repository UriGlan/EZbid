import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";

const ProfileEditComponents = (profile) => {
const fields = [
    { id: 'username', label: 'User Name', disabled: true, placeholder: profile.profile.username },
    { id: 'firstName', label: 'First Name', disabled: false, placeholder: profile.profile.firstName },
    { id: 'lastName', label: 'Last Name', disabled: false, placeholder: profile.profile.lastName },
    { id: 'email', label: 'Email Address', disabled: true, placeholder: profile.profile.email },
];
    return (
        <Grid container spacing={2}>
            {fields.map((field) => (
                <Grid item xs={12} key={field.id}>
                    <TextField
                        id={field.id}
                        label={field.label}
                        disabled={field.disabled}
                        value={field.placeholder || ''}

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
                                '&::placeholder': {
                                    color: 'black', // Change label color
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
                            shrink: true,
                        }}
                    />
                </Grid>
            ))}
        </Grid>
    );
};

export default ProfileEditComponents;
