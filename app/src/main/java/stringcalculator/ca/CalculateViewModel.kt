package stringcalculator.ca

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculateViewModel: ViewModel() {

    private val _exceptions = MutableLiveData<List<Int>>()
    val exceptions: LiveData<List<Int>> = _exceptions

    private val _result = MutableLiveData<Int>()
    val result: LiveData<Int> = _result

    fun calculate(str: String) {
        val resultOfCalc = Utils.add(str)
        if(resultOfCalc >= 0) {
            _result.value = resultOfCalc
        }else{
            _exceptions.value = Utils.exceptions
        }
    }
}
