package com.example.beneson.idrugs;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matteo on 24/08/2015.
 * Updated on 19/06/2016 following https://firebase.google.com/support/guides/firebase-android.
 * <p>
 * This class is a generic way of backing an Android RecyclerView with a Firebase location.
 * It handles all of the child events at the given Firebase location.
 * It marshals received data into the given class type.
 * Extend this class and provide an implementation of the abstract methods, which will notify when
 * the adapter list changes.
 * <p>
 * This class also simplifies the management of configuration change (e.g.: device rotation)
 * allowing the restore of the list.
 *
 * @param <T> The class type to use as a model for the data contained in the children of the
 *            given Firebase location
 */
public abstract class FirebaseRecyclerAdapter<ViewHolder extends RecyclerView.ViewHolder, T>
        extends RecyclerView.Adapter<ViewHolder> {

    private String mWhereClause;
    private Class<Produto> mItemClass;
    private ArrayList<Produto> mItems;
    private ArrayList<String> mKeys;


    public FirebaseRecyclerAdapter(String WhereClause, Class<Produto> itemClass) {
        this(WhereClause, itemClass, null, null);
    }

    /**
     * @param whereClause The Firebase location to watch for data changes.
     *                    Can also be a slice of a location, using some combination of
     *                    <code>limit()</code>, <code>startAt()</code>, and <code>endAt()</code>.
     * @param itemClass   The class of the items.
     * @param items       List of items that will load the adapter before starting the listener.
     *                    Generally null or empty, but this can be useful when dealing with a
     *                    configuration change (e.g.: reloading the adapter after a device rotation).
     *                    Be careful: keys must be coherent with this list.
     * @param keys        List of keys of items that will load the adapter before starting the listener.
     *                    Generally null or empty, but this can be useful when dealing with a
     *                    configuration change (e.g.: reloading the adapter after a device rotation).
     *                    Be careful: items must be coherent with this list.
     */
    public FirebaseRecyclerAdapter(String whereClause, Class<Produto> itemClass,
                                   @Nullable ArrayList<Produto> items,
                                   @Nullable ArrayList<String> keys) {

        this.mWhereClause = whereClause;

        if (items != null && keys != null) {
            this.mItems = items;
            this.mKeys = keys;
            Log.i("tagx", "Items e keys != null");
        } else {
            Log.i("tagx", "Items = null ou/e keys = null");
            mItems = new ArrayList<Produto>();
            mKeys = new ArrayList<String>();
        }

        this.mItemClass = itemClass;
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(whereClause);
        Log.i("tagx", "Persistence ativada");

        Backendless.Persistence.of(Produto.class).find(dataQuery, callback);

    }


    private AsyncCallback<BackendlessCollection<Produto>> callback = new AsyncCallback<BackendlessCollection<Produto>>() {

        @Override
        public void handleResponse(BackendlessCollection<Produto> resultadoPesquisa) {

            final List<Produto> resultado = resultadoPesquisa.getCurrentPage();

                        for (int i = 0; i < resultado.size(); i++) {

                            String key = resultado.get(i).getObjectId();

                            if (!mKeys.contains(key)) {
                                Produto item = resultado.get(i);
                                mItems.add(item);
                                mKeys.add(key);
                                Log.i("tagx", "Produto adicionado");
                            }

                        }
        }

        @Override
        public void handleFault(BackendlessFault backendlessFault) {
            Log.i("tagx", "Erro no Backendless: " + backendlessFault.toString());
            //Toast.makeText(getApplicationContext(), "Problemas na Conexão. Verifique se há disponibilidade na rede.", Toast.LENGTH_SHORT).show();
        }

    };


    @Override
    public abstract ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(ViewHolder holder, final int position);

    @Override
    public int getItemCount() {
        return (mItems != null) ? mItems.size() : 0;
    }

    /**
     * Clean the adapter.
     * ALWAYS call this method before destroying the adapter to remove the listener.
     */
    public void destroy() {
        //mQuery.removeEventListener(mListener);
    }

    /**
     * Returns the list of items of the adapter: can be useful when dealing with a configuration
     * change (e.g.: a device rotation).
     * Just save this list before destroying the adapter and pass it to the new adapter (in the
     * constructor).
     *
     * @return the list of items of the adapter
     */
    public ArrayList<Produto> getItems() {
        return mItems;
    }

    /**
     * Returns the list of keys of the items of the adapter: can be useful when dealing with a
     * configuration change (e.g.: a device rotation).
     * Just save this list before destroying the adapter and pass it to the new adapter (in the
     * constructor).
     *
     * @return the list of keys of the items of the adapter
     */
    public ArrayList<String> getKeys() {
        return mKeys;
    }

    /**
     * Returns the item in the specified position
     *
     * @param position Position of the item in the adapter
     * @return the item
     */
    public Produto getItem(int position) {
        return mItems.get(position);
    }

    /**
     * Returns the position of the item in the adapter
     *
     * @param item Item to be searched
     * @return the position in the adapter if found, -1 otherwise
     */
    public int getPositionForItem(Produto item) {
        return mItems != null && mItems.size() > 0 ? mItems.indexOf(item) : -1;
    }

    /**
     * Check if the searched item is in the adapter
     *
     * @param item Item to be searched
     * @return true if the item is in the adapter, false otherwise
     */
    public boolean contains(Produto item) {
        return mItems != null && mItems.contains(item);
    }

    /**
     * ABSTRACT METHODS THAT MUST BE IMPLEMENTED BY THE EXTENDING ADAPTER.
     */

    /**
     * Called after an item has been added to the adapter
     *
     * @param item     Added item
     * @param key      Key of the added item
     * @param position Position of the added item in the adapter
     */
    protected abstract void itemAdded(Produto item, String key, int position);

    /**
     * Called after an item changed
     *
     * @param oldItem  Old version of the changed item
     * @param newItem  Current version of the changed item
     * @param key      Key of the changed item
     * @param position Position of the changed item in the adapter
     */
    protected abstract void itemChanged(Produto oldItem, Produto newItem, String key,
                                        int position);

    /**
     * Called after an item has been removed from the adapter
     *
     * @param item     Removed item
     * @param key      Key of the removed item
     * @param position Position of the removed item in the adapter
     */
    protected abstract void itemRemoved(T item, String key, int position);

    /**
     * Called after an item changed position
     *
     * @param item        Moved item
     * @param key         Key of the moved item
     * @param oldPosition Old position of the changed item in the adapter
     * @param newPosition New position of the changed item in the adapter
     */
    protected abstract void itemMoved(T item, String key, int oldPosition, int newPosition);

}
