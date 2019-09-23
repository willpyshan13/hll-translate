package me.jessyan.armscomponent.commonsdk.utils

import java.text.SimpleDateFormat

/**
 * Desc:时间工具
 *
 * Date: 2019-09-23 17:13
 * Copyright: Copyright (c) 2018-2019
 * Company: @微微科技有限公司
 * Updater:
 * Update Time:
 * Update Comments:
 * @author: pengysh
 */
class TimeUtils{

    companion object{
        val format = SimpleDateFormat("HH:mm")

        val formatDate = SimpleDateFormat("MM-dd")
        fun getFormatTime(time:Long):String{
            return format.format(time)
        }

        fun getFormatDate(day:Long):String{
            return formatDate.format(day)
        }
    }

}