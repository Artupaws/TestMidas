package com.putra.test.ui.mainpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.putra.test.MainActivity
import com.putra.test.R
import com.putra.test.adapter.AdapterPhotos
import com.putra.test.adapter.AdapterUser
import com.putra.test.databinding.FragmentMainPageBinding
import com.putra.test.model.ResponseGetData
import com.putra.test.roomdatauser.DataUser
import com.putra.test.roomdatauser.RoomDataUser
import com.putra.test.roomdatauser.UserDao

class MainPageFragment : Fragment(), MainPageContract, View.OnClickListener {

    private lateinit var _binding:FragmentMainPageBinding
    private val binding get() = _binding
    private lateinit var presenter: MainPagePresenter
    private lateinit var data: DataUser
    private lateinit var database: RoomDataUser
    private lateinit var dao: UserDao
    private lateinit var listDataUser: ArrayList<DataUser>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        presenter = MainPagePresenter(this)
        binding.btnLogout.setOnClickListener(this)
        database = RoomDataUser.getDatabase(requireActivity())
        dao = database.getDataUser()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        data = arguments?.getParcelable("dataLogin")!!
        presenter.getDataApi()
        setupProfile(data)
        getListDataPassengerDao(database, dao)
        if (data.role == "Admin"){
            binding.rvUser.visibility = View.VISIBLE
            binding.rvPhotos.visibility = View.GONE
        } else if(data.role == "User") {
            binding.rvUser.visibility = View.GONE
            binding.rvPhotos.visibility = View.VISIBLE
        }
    }

    override fun messageGetData(msg: String) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun getData(data: List<ResponseGetData?>?) {
        val adapterUser = AdapterPhotos(requireContext(), data)
        binding.rvPhotos.apply {
            adapter = adapterUser
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupProfile(data:DataUser){
        binding.toolbar2.subtitle = "Hi, ${data.username} - Role : ${data.role}"
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_logout -> {
                Navigation.findNavController(v).navigateUp()
            }
        }
    }

    private fun getListDataPassengerDao(database: RoomDataUser, dao: UserDao) {
        listDataUser = arrayListOf<DataUser>()
        listDataUser.addAll(dao.getAll())
        binding.rvUser.apply {
            adapter = AdapterUser(requireActivity(), listDataUser)
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        }
    }

}