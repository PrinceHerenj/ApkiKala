package com.example.answerandquestion
//
//import android.content.Context
//import android.os.Message
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//class mesgAdapter(val context: Context, val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        return when (viewType) {
//            SENT_VIEW_TYPE -> {
//                val view = inflater.inflate(R.layout.send, parent, false)
//                SentViewHolder(view)
//            }
//            else -> {
//                val view = inflater.inflate(R.layout.receive, parent, false)
//                ReceiveViewHolder(view)
//            }
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val currentMessage = messageList[position]
//
//        when (holder.itemViewType) {
//            SENT_VIEW_TYPE -> {
//                val viewHolder = holder as SentViewHolder
//                viewHolder.sentMessage.text = currentMessage.obj.toString()
//            }
//            else -> {
//                val viewHolder = holder as ReceiveViewHolder
//                viewHolder.receiveMessage.text = currentMessage.obj.toString()
//            }
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return messageList.size
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return if (messageList[position].what == SENT_VIEW_TYPE) {
//            SENT_VIEW_TYPE
//        } else {
//            RECEIVE_VIEW_TYPE
//        }
//    }
//
//    companion object {
//        const val SENT_VIEW_TYPE = 1
//        const val RECEIVE_VIEW_TYPE = 2
//    }
//
//    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val sentMessage = itemView.findViewById<TextView>(R.id.txt_sent_message)
//    }
//
//    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val receiveMessage = itemView.findViewById<TextView>(R.id.txt_receive_message)
//    }
//}
