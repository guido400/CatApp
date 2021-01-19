package com.example.android.catapp.ui


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.android.catapp.R
import com.example.android.catapp.databinding.FragmentCreateCatBinding
import com.example.android.catapp.db.Cat
import com.example.android.catapp.db.CatDatabase



/**
 * A simple [Fragment] subclass.
 * Use the [CreateCatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateCatFragment : Fragment() {
    var createCatBinding: FragmentCreateCatBinding? = null
    lateinit var catImageAdapter: CustomDropDownAdapter
    private lateinit var viewModel:MainActivityViewModel
    private var imageId: Int? = null


    val catImages = listOf(
        R.drawable.cat_banjo_icon,
        R.drawable.cat_bat_icon,
        R.drawable.cat_drunk_icon,
        R.drawable.cat_grumpy_icon,
        R.drawable.cat_hungry_icon,
        R.drawable.cat_poo_icon,
        R.drawable.cat_rascal_icon,
        R.drawable.cat_sleep_icon
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)}



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCreateCatBinding.inflate(inflater, container, false)

        binding.buttonCreate.setOnClickListener {
            addCat()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)



        }

        catImageAdapter = CustomDropDownAdapter(this,catImages)
        binding.spinnerImage.adapter = catImageAdapter
        binding.spinnerImage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                imageId = when (p1?.id) {
                    R.drawable.cat_banjo_icon -> 0
                    R.drawable.cat_bat_icon -> 1
                    R.drawable.cat_drunk_icon -> 2
                    R.drawable.cat_grumpy_icon -> 3
                    R.drawable.cat_hungry_icon -> 4
                    R.drawable.cat_poo_icon -> 5
                    R.drawable.cat_rascal_icon -> 6
                    R.drawable.cat_sleep_icon -> 7
                    else -> null
                }


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                /* no-op */
            }


        }




        createCatBinding = binding
        // Inflate the layout for this fragment

        val application = requireNotNull(this.activity).application
        val catDao = CatDatabase.getInstance(application).catDao
        viewModel = ViewModelProvider(this,MainActivityViewModelFactory(catDao)).get(MainActivityViewModel::class.java)


        return binding.root
    }

    fun addCat () {
        val age:Int = createCatBinding?.editTextAge?.text.toString().toInt()
        val name:String = createCatBinding?.editTextName?.text.toString()
        val gender:Char = if (createCatBinding?.radioButtonFemale?.isSelected == true)  'f' else 'm'


        val newCat = Cat (name,imageId,gender,age)
        viewModel.addCat(newCat)
    }


}


class CustomDropDownAdapter(val context: CreateCatFragment, var dataSource: List<Int>) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.layoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.spinner_icon, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }

        val id = dataSource[position]

        vh.img.setBackgroundResource(id)

        return view
    }

    override fun getItem(position: Int): Any? {
        return dataSource[position];
    }

    override fun getCount(): Int {
        return dataSource.size;
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    private class ItemHolder(row: View?) {

        val img: ImageView

        init {

            img = row?.findViewById(R.id.imageView) as ImageView
        }
    }
}









