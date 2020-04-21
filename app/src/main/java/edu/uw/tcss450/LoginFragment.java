package edu.uw.tcss450;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.uw.tcss450.databinding.FragmentLoginBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private FragmentLoginBinding binding;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.register.setOnClickListener(this);
        binding.signIn.setOnClickListener(this::jumpSuccess);

    }

    /**
     *
     * @param password that must be valid
     * @return TRUE if the password is STRONG -> ADAM@@!@12242 EXAMPLE
     */
    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding= null;
    }


    /**
     *
     * @param view a button
     * @Jumps into Register fragment
     */
    public void jumpLogin(View view) {
        String str_email = binding.emailView.getText().toString();
        String str_pass = binding.passView.getText().toString();
        if((str_email.isEmpty()) &&(str_pass.isEmpty())){
            //        NavController navController= Navigation.findNavController(view);
//        navController.navigate(R.id.action_loginFragment_to_registerFragment());
            LoginFragmentDirections.ActionLoginFragmentToRegisterFragment directions =
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment(str_email, str_pass);

            Navigation.findNavController(getView()).navigate(directions);
        }
        else {
            if(!validate_string()){
                return;
            }else{
                //        NavController navController= Navigation.findNavController(view);
//        navController.navigate(R.id.action_loginFragment_to_registerFragment());
                LoginFragmentDirections.ActionLoginFragmentToRegisterFragment directions =
                        LoginFragmentDirections.actionLoginFragmentToRegisterFragment(str_email, str_pass);

                Navigation.findNavController(getView()).navigate(directions);
            }
        }
    }

    /**
     *
     * @param view a button
     * @Jumps into SuccessFragment
     */
    public void jumpSuccess(View view){
        if(!validate_string()){
            return;
        }
        else {
            String str_email = binding.emailView.getText().toString();
            String str_pass = binding.passView.getText().toString();
//        NavController navController= Navigation.findNavController(view);
//        navController.navigate(R.id.action_loginFragment_to_registerFragment());
            LoginFragmentDirections.ActionLoginFragmentToMainActivity directions =
                    LoginFragmentDirections.actionLoginFragmentToMainActivity(str_email, "");

            Navigation.findNavController(getView()).navigate(directions);
        }


    }

    /**
     *
     * @param v SIGN IN HAS CLICKED
     */
    public void sign_clicked(View v){

    }


    @Override
    public void onClick(View v) {
        if(v==binding.register)
            jumpLogin(v);

    }

    boolean flag = false;
    private boolean validate_string(){
        flag = true;
        String str_email = binding.emailView.getText().toString();
        String str_pass = binding.passView.getText().toString();
//        if( str_email.length() < 5)
//            Toast.makeText(getContext(), "email is too short", Toast.LENGTH_SHORT).show();

        if(!str_email.contains("@")){
            Toast.makeText(getContext(), "email must be valid with @something and not empty", Toast.LENGTH_SHORT).show();
            //  binding.emailView.setError("email must be valid with @something and not empty");
            flag=false;
            return false;
        }
        if(str_pass.isEmpty()){
            Toast.makeText(getContext(), "password must not be empty", Toast.LENGTH_SHORT).show();
            // binding.passView.setError("password must not be empty");
            flag=false;
            return false;
        }
        if(!isValidPassword(str_pass)) {
            Toast.makeText(getContext(), "password must be strong", Toast.LENGTH_SHORT).show();
            //  binding.passView.setError("password must  be strong");
            flag=false;
            return false;
        }

        if(str_email.length() <5){
            Toast.makeText(getContext(), "email must be at least 5 characters", Toast.LENGTH_SHORT).show();
            //  binding.passView.setError("email must be at least 5 characters");
            flag=false;
            return false;
        }

        return flag;
    }

}
