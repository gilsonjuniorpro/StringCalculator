package stringcalculator.ca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import stringcalculator.ca.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CalculateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener {
            binding.textViewErrorMessage.visibility = View.GONE
            val str = binding.editTextValue.text.toString()
            viewModel.calculate(str)
        }

        viewModel.exceptions.observe(this){ exception ->
            binding.textViewResult.text = exception.toString()
            binding.textViewErrorMessage.text = getString(R.string.negatives_not_allowed)
            binding.textViewErrorMessage.visibility = View.VISIBLE
        }

        viewModel.result.observe(this){ result ->
            binding.textViewResult.text = getString(R.string.the_result_is, result.toString())
        }
    }
}
