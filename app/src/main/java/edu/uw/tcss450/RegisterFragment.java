package edu.uw.tcss450;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.uw.tcss450.databinding.FragmentLoginBinding;
import edu.uw.tcss450.databinding.FragmentRegisterBinding;


public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentRegisterBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if(!isFrag()){
            binding.register2.setOnClickListener(button -> jumpLoginEmpty(view));
        }
    }

    private boolean isFrag(){
        Log.d("args VALUEEE",  "PROGRAM IS FIRST LINE IN FRAG ");

        RegisterFragmentArgs args = RegisterFragmentArgs.fromBundle(getArguments());
        Log.d("args VALUEEE", args+ " ");
        binding.emailView.setText(args.getEmailFromLogin());
        binding.passView.setText(args.getPasswordFromLogin());
        binding.passAgain.setText(args.getPasswordFromLogin());
        boolean flag = args.getEmailFromLogin().isEmpty() && args.getPasswordFromLogin().isEmpty();

        Log.d("check is Frag", flag + "");
        return (args.getEmailFromLogin().isEmpty() && args.getPasswordFromLogin().isEmpty());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding= null;
    }



    public void jumpLogin(View view, String str_email, String str_pass){

        if(!str_email.contains("@")&& (!str_email.isEmpty())){
            Toast.makeText(getContext(), "Please type your email correctly with ->  @", Toast.LENGTH_SHORT).show();
        }
        else if(str_email.isEmpty()|| str_pass.isEmpty()){
            Toast.makeText(getContext(), "Please type something", Toast.LENGTH_SHORT).show();
        }
//        else if(!str_pass.equals(pass_again)){
//            Toast.makeText(getContext(), "pass not equal", Toast.LENGTH_SHORT).show();
//        }
        else{
            RegisterFragmentDirections.ActionRegisterFragmentToSuccessFragment directions =
                    RegisterFragmentDirections.actionRegisterFragmentToSuccessFragment(str_email, "");

            //Use the navigate method to perform the navigation.
            Navigation.findNavController(getView()).navigate(directions);

        }
    }

    public void jumpLoginEmpty(View view){
        String str = binding.emailView.getText().toString();
        String pass = binding.passView.getText().toString();
        String pass_again = binding.passAgain.getText().toString();


        if(!str.contains("@")&& (!str.isEmpty())){
            Toast.makeText(getContext(), "Please type your email correctly with ->  @", Toast.LENGTH_SHORT).show();
        }
        else if(str.isEmpty()|| pass.isEmpty() || pass_again.isEmpty()){
            Toast.makeText(getContext(), "Please type something for password field", Toast.LENGTH_SHORT).show();
        }
        else if(!pass.equals(pass_again)){
            Toast.makeText(getContext(), "pass not equal", Toast.LENGTH_SHORT).show();
        }
        else{
            RegisterFragmentDirections.ActionRegisterFragmentToSuccessFragment directions =
                    RegisterFragmentDirections.actionRegisterFragmentToSuccessFragment(str, "");

            //Use the navigate method to perform the navigation.
            Navigation.findNavController(getView()).navigate(directions);

        }

    }



}
