package com.putra.test.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.Navigation
import com.putra.test.R
import com.putra.test.databinding.FragmentRegisterBinding
import com.putra.test.roomdatauser.DataUser
import com.putra.test.roomdatauser.RoomDataUser
import com.putra.test.roomdatauser.UserDao

class RegisterFragment : Fragment(), View.OnClickListener {

    private lateinit var _binding:FragmentRegisterBinding
    private val binding get() = _binding
    private var roleRadio:String? = null
    private lateinit var database: RoomDataUser
    private lateinit var dao:UserDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        database = RoomDataUser.getDatabase(requireActivity())
        dao = database.getDataUser()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
        binding.btnRegister.setOnClickListener(this)
        radioGroup()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_register -> {
                register()
            }
        }
    }

    private fun register(){
        var isEmptyUsername = false
        var isEmptyEmail = false
        var isEmptyPassword = false
        var isEmptyRole = false

        var username = binding.etUsername.text.toString()
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var role = roleRadio

        if (username.isNullOrEmpty()){
            binding.etUsername.error = "Empty"
            isEmptyUsername = true
        } else {
            username = binding.etUsername.text.toString()
            isEmptyUsername = false
        }

        if (email.isNullOrEmpty()){
            binding.etEmail.error = "Empty"
            isEmptyEmail = true
        } else {
            email = binding.etEmail.text.toString()
            isEmptyEmail = false
        }

        if (password.isNullOrEmpty()){
            binding.etPassword.error = "Empty"
            isEmptyPassword = true
        } else {
            password = binding.etPassword.text.toString()
            isEmptyPassword = false
        }

        if (role.isNullOrEmpty()){
            binding.rbAdmin.error = "Empty"
            binding.rbUser.error = "Empty"
            isEmptyRole = true
        } else {
            role = roleRadio
            isEmptyRole = false
        }

        if (!isEmptyUsername && !isEmptyEmail && !isEmptyRole && !isEmptyPassword){
            saveDataUser(DataUser(0,username,role!!,email,password))
        } else {
            Toast.makeText(requireActivity(), "Please complete data", Toast.LENGTH_SHORT).show()
        }

    }

    private fun saveDataUser(dataUser: DataUser){
        if (dao.getById(dataUser.id).isEmpty()){
            dao.insert(dataUser)
            Toast.makeText(requireActivity(), "Register Success", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(binding.toolbar).navigateUp()
        }else {
            dao.update(dataUser)
        }
    }

    private fun radioGroup(){
        binding.rdGroup.setOnCheckedChangeListener { group, checkedId ->
            val radio : RadioButton = requireActivity().findViewById(checkedId)
            radioButton(radio)
        }
    }

    private fun radioButton(view: View){
        val radio: RadioButton = requireActivity().findViewById(binding.rdGroup.checkedRadioButtonId)
        roleRadio = radio.text.toString()
        if(roleRadio!!.isNotEmpty()){
            binding.rbAdmin.error = null
            binding.rbUser.error = null
        }
    }


}