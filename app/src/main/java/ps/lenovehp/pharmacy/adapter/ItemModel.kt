package ps.lenovehp.pharmacy.adapter

import android.media.Image

class ItemModel(
        var ID: Int,
        var generalName: String,
        var tradename: String,
        var company: Int,
        var category: Int,
        var productionDate: String,
        var expirationdate: String,
        var price: Float,
        var description: String,
        var unitsnumber: Float,
        var shelf: String,
        var img: Image?


)
{
}