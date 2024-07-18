package adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.codewithom.petshopee.R;

import java.util.List;

public class mycartAdapter1 extends RecyclerView.Adapter<mycartAdapter1.ViewHolder> {

    Context context;
    List<mycartAdapter> mycartAdapterList;
    FirebaseFirestore db;
    FirebaseAuth auth;

    public mycartAdapter1(Context context, List<mycartAdapter> mycartAdapterList) {
        this.context = context;
        this.mycartAdapterList = mycartAdapterList;
        this.db = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        mycartAdapter currentItem = mycartAdapterList.get(position);

        holder.name.setText(currentItem.getProduct());
        holder.date.setText(currentItem.getCurrentDate());
        holder.time.setText(currentItem.getCurrentTime());
        holder.quantity.setText(String.valueOf(currentItem.getTotalquantity()));

        int amountToShow = currentItem.getAmount();
        holder.amount.setText(String.valueOf(amountToShow));

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCartItem(position);
            }
        });

        calculateTotalPrice();
    }

    @Override
    public int getItemCount() {
        return mycartAdapterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, amount, date, time, quantity;
        ImageView deleteItem;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product);
            amount = itemView.findViewById(R.id.amount);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            quantity = itemView.findViewById(R.id.totalquantity);
            deleteItem = itemView.findViewById(R.id.delete);
        }
    }

    private void deleteCartItem(int position) {
        db.collection("Petshopee")
                .document(auth.getCurrentUser().getUid())
                .collection("CurrentUsers")
                .document(mycartAdapterList.get(position).getDocumentId())
                .delete()
                .addOnSuccessListener(aVoid -> {
                    mycartAdapterList.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
                    calculateTotalPrice();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Failed to delete item", Toast.LENGTH_SHORT).show();
                });
    }

    private void calculateTotalPrice() {
        int totalPrice = 0;
        for (mycartAdapter item : mycartAdapterList) {
            totalPrice += item.getTotalquantity() * item.getAmount();
        }
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount", totalPrice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
