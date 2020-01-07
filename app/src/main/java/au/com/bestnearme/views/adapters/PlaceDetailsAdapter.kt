package au.com.bestnearme.views.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.com.bestnearme.R
import au.com.bestnearme.data.placedetails.PlaceDetails
import au.com.bestnearme.views.placedetails.PlaceDetailsCellType
import au.com.bestnearme.views.placedetails.viewholders.*

class PlaceDetailsAdapter (private var mPlaceDetailsCellTypes: ArrayList<PlaceDetailsCellType>? = arrayListOf(),
                       var mPlaceDetails: PlaceDetails? = null,
                       var mTrueRating: Double = 0.0) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val viewPool = RecyclerView.RecycledViewPool()
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PlaceDetailsCellType.MAP.ordinal -> MapViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_map_row, parent, false))
            PlaceDetailsCellType.PHOTOS.ordinal -> PhotosViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_photos_row, parent, false))
            PlaceDetailsCellType.REVIEWS.ordinal -> ReviewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_reviews_row, parent, false))
            else -> HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_header_row, parent, false))

        }
    }

    override fun getItemCount(): Int {
        mPlaceDetailsCellTypes?.let {
            return it.size
        }  ?: run {
            return 0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int) {
        when (getItemViewType(position)) {
            PlaceDetailsCellType.HEADER.ordinal -> {
                val headerViewHolder = holder as HeaderViewHolder
                headerViewHolder.bind(mPlaceDetails,mTrueRating)
            }
            PlaceDetailsCellType.MAP.ordinal -> {
                val mapViewHolder = holder as MapViewHolder
                mapViewHolder.bind(mPlaceDetails?.geometry?.location,mPlaceDetails?.name!!)
            }
            PlaceDetailsCellType.PHOTOS.ordinal -> {
                val photosViewHolder = holder as PhotosViewHolder
                mPlaceDetails?.photos?.let {
                    photosViewHolder.bind(it)
                }
            }
            PlaceDetailsCellType.REVIEWS.ordinal -> {
                val reviewsViewHolder = holder as ReviewsViewHolder
                mPlaceDetails?.reviews?.let {
                    reviewsViewHolder.bind(it)
                }
            }
            /*DashboardCellType.BILLS.ordinal -> {
                if(holder is DashboardViewHolder) {
                    val upcomingBillsAdapter = UpcomingBillsAdapter(mOnBillsClickListener, false)
                    upcomingBillsAdapter.mUpcomingBills = mOverviewResponse.upcomingBills
                    holder.bind(
                        getResources().getString(R.string.title_upcoming_bills),
                        upcomingBillsAdapter,
                        true,
                        mOverviewResponse.upcomingBills?.isEmpty(),
                        DashboardCellType.BILLS.ordinal
                    )
                }
            }
            DashboardCellType.CATEGORIES.ordinal -> {
                val categoriesViewHolder = holder as DashboardViewHolder
                val statsAdapter = StatsAdapter(mOnCategoryClickListener)
                statsAdapter.mCategories =mOverviewResponse.topCategoriesAmount!!
                categoriesViewHolder.bind(getResources().getString(R.string.title_money_spent),statsAdapter,false,
                    mOverviewResponse.topCategoriesAmount?.isEmpty(),
                    DashboardCellType.CATEGORIES.ordinal)
            }
            DashboardCellType.TRANSACTIONS.ordinal -> {
                val transactionsViewHolder = holder as DashboardViewHolder
                val transactionsAdapter = TransactionsAdapter(object : OnTransactionClickListener{
                    override fun onTransactionClicked(transactionsItem: TransactionsItem) {
                        AnalyticsClient.trackClickEvent(AnalyticsClient.SCREEN_SPEND_SPENT_DASH,
                            AnalyticsClient.COMPONENT_SPEND_ACTIVITY_CLICK)
                        mOnTransactionAndAccountClickListener?.onTransactionClicked(transactionsItem)
                    }
                })
                mOverviewResponse.recentTransactions?.let{transactionsAdapter.mSpendings=it}
                transactionsViewHolder.bind(getResources().getString(R.string.label_recent_transactions),transactionsAdapter,false,
                    mOverviewResponse.recentTransactions?.isEmpty(),
                    DashboardCellType.TRANSACTIONS.ordinal)
            }
            DashboardCellType.OVERVIEW.ordinal -> {
                val spendingOverviewViewHolder = holder as SpendingOverviewViewHolder
                spendingOverviewViewHolder.bindView(mOverviewResponse.overviewCard)
            }*/


        }
    }


    override fun getItemViewType(position: Int): Int {
        mPlaceDetailsCellTypes?.let {
            return it[position].ordinal
        }  ?: run {
            return -1
        }
    }

    fun updateSections(newPlaceDetailsCellTypes: ArrayList<PlaceDetailsCellType>,
                       newPlaceDetails: PlaceDetails,
                       trueRating : Double) {
        mPlaceDetailsCellTypes?.clear()
        mPlaceDetailsCellTypes?.addAll(newPlaceDetailsCellTypes)
        mPlaceDetails = newPlaceDetails
        mTrueRating = trueRating
        Log.w("TAG","Places "+mPlaceDetailsCellTypes)
        notifyDataSetChanged()
    }

}