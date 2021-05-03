package com.putra.test.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.putra.test.R
import com.putra.test.databinding.FragmentLoginBinding
import com.putra.test.roomdatauser.DataUser
import com.putra.test.roomdatauser.RoomDataUser
import com.putra.test.roomdatauser.UserDao

class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var _binding:FragmentLoginBinding
    private val binding get() = _binding
    private lateinit var database: RoomDataUser
    private lateinit var dao: UserDao
    private lateinit var listDataUser: ArrayList<DataUser>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        database = RoomDataUser.getDatabase(requireActivity())
        dao = database.getDataUser()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnRegister.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
        getListDataPassengerDao(database, dao)
    }

    override fun onResume() {
        super.onResume()
        getListDataPassengerDao(database, dao)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_login -> {
                checkLogin()
            }

            R.id.btn_register -> {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

    private fun checkLogin() {
        var isEmptyEmail = false
        var isEmptyPassword = false

        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()


        if (email.isNullOrEmpty()) {
            binding.etEmail.error = "Empty"
            isEmptyEmail = true
        } else {
            email = binding.etEmail.text.toString()
            isEmptyEmail = false
        }

        if (password.isNullOrEmpty()) {
            binding.etPassword.error = "Empty"
            isEmptyPassword = true
        } else {
            password = binding.etPassword.text.toString()
            isEmptyPassword = false
        }

        if (!isEmptyEmail && !isEmptyPassword){
            val user : DataUser? = dao.getUser(email, password)
            if (user != null){
                val bundle = Bundle()
                bundle.putParcelable("dataLogin", user)
                Navigation.findNavController(binding.btnLogin).navigate(R.id.action_loginFragment_to_mainPageFragment, bundle)
            } else {
                Toast.makeText(requireActivity(), "Account not found!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireActivity(), "Please complete form", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getListDataPassengerDao(database: RoomDataUser, dao: UserDao) {
        listDataUser = arrayListOf<DataUser>()
        listDataUser.addAll(dao.getAll())
    }
}