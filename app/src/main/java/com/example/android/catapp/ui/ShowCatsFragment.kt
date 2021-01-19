package com.example.android.catapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.android.catapp.R
import com.example.android.catapp.databinding.FragmentShowCatsBinding
import com.example.android.catapp.db.Cat
import com.example.android.catapp.db.CatDatabase

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ShowCatsFragment : Fragment() {

    var showCatsBinding:FragmentShowCatsBinding? = null
    lateinit var adapter: CatAdapter
    private lateinit var viewModel:MainActivityViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View {
        adapter = CatAdapter()
        val binding = FragmentShowCatsBinding.inflate(inflater, container, false)
        binding.showCatsView.adapter = adapter

        val application = requireNotNull(this.activity).application
        val catDao = CatDatabase.getInstance(application).catDao
        viewModel = ViewModelProvider(this,MainActivityViewModelFactory(catDao)).get(MainActivityViewModel::class.java)
        
        viewModel.cats.observe(viewLifecycleOwner, Observer{ it.let{adapter.catData = it} })



        showCatsBinding = binding
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onDestroyView() {
        showCatsBinding = null
        super.onDestroyView()
    }




}

class CatAdapter: RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    var catData = listOf<Cat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.cat_presenter, parent, false)
        return CatViewHolder(view)


    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val item = catData[position]
        val res = holder.itemView.context.resources

        val nameText = "Name: $item.name"
        val ageText = "Age: $item.age"
        val genderLongName = if (item.gender == 'f') "Female" else "Male"
        val genderText = "Gender: $genderLongName"

        holder.nameText.text = nameText
        holder.ageText.text = ageText
        holder.genderText.text = genderText

        val catImageId = when (item.imageId) {
            0 -> R.drawable.cat_banjo_icon
            1 -> R.drawable.cat_bat_icon
            2 -> R.drawable.cat_drunk_icon
            3 -> R.drawable.cat_grumpy_icon
            4 -> R.drawable.cat_hungry_icon
            5 -> R.drawable.cat_poo_icon
            6 -> R.drawable.cat_rascal_icon
            7 -> R.drawable.cat_sleep_icon
            else -> R.drawable.ic_baseline_help
        }

        holder.catImageView.setImageResource(catImageId)


    }

    override fun getItemCount(): Int {
        return catData.size
    }


    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.name_text)
        val genderText: TextView = itemView.findViewById(R.id.gender_text)
        val ageText: TextView = itemView.findViewById(R.id.age_text)
        val catImageView: ImageView = itemView.findViewById(R.id.imageView_cat)

    }
}
