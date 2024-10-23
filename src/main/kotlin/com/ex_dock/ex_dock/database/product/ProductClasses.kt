package com.ex_dock.ex_dock.database.product

import com.ex_dock.ex_dock.database.category.PageIndex
import com.sun.org.apache.xpath.internal.operations.Bool

data class Products(
  var productId: Int,
  var name: String,
  var shortName: String,
  var description: String,
  var shortDescription: String
)

data class ProductsSeo(
  var productId: Int,
  var metaTitle: String,
  var metaDescription: String,
  var metaKeywords: String,
  var pageIndex: PageIndex
)

data class ProductsPricing(
  var productId: Int,
  var price: Double,
  var salePrice: Double,
  var costPrice: Double
)

data class CustomProductAttributes(
  var attributeKey: String,
  var scope: Int,
  var name: String,
  var type: Type,
  var multiselect: Boolean,
  var required: Boolean
)

data class EavGlobalBool(
  var productId: Int,
  var attributeKey: String,
  var value: Boolean
)

data class EavGlobalFloat(
  var productId: Int,
  var attributeKey: String,
  var value: Float
)

data class EavGlobalString(
  var productId: Int,
  var attributeKey: String,
  var value: String
)

data class EavGlobalInt(
  var productId: Int,
  var attributeKey: String,
  var value: Int
)

data class EavGlobalMoney(
  var productId: Int,
  var attributeKey: String,
  var value: Double
)

data class EavGlobalMultiSelect(
  var productId: Int,
  var attributeKey: String,
  var value: Int
)

data class Eav(
  var productId: Int,
  var attributeKey: String,
)

data class EavWebsiteBool(
  var productId: Int,
  var websiteId: Int,
  var attributeKey: String,
  var value: Boolean
)

data class EavWebsiteFloat(
  var productId: Int,
  var websiteId: Int,
  var attributeKey: String,
  var value: Float
)

data class EavWebsiteString(
  var productId: Int,
  var websiteId: Int,
  var attributeKey: String,
  var value: String,
)

data class EavWebsiteInt(
  var productId: Int,
  var websiteId: Int,
  var attributeKey: String,
  var value: Int
)

data class EavWebsiteMoney(
  var productId: Int,
  var websiteId: Int,
  var attributeKey: String,
  var value: Double
)

data class EavWebsiteMultiSelect(
  var productId: Int,
  var websiteId: Int,
  var attributeKey: String,
  var value: Int
)

data class EavWebsiteInfo(
  val eav: Eav,
  val eavWebsiteBool: EavWebsiteBool,
  val eavWebsiteFloat: EavWebsiteFloat,
  val eavWebsiteString: EavWebsiteString,
  val eavWebsiteInt: EavWebsiteInt,
  val eavWebsiteMoney: EavWebsiteMoney,
  val eavWebsiteMultiSelect: EavWebsiteMultiSelect
)

data class EavStoreViewBool(
  var productId: Int,
  var storeViewId: Int,
  var attributeKey: String,
  var value: Boolean
)

data class EavStoreViewFloat(
  var productId: Int,
  var storeViewId: Int,
  var attributeKey: String,
  var value: Float
)

data class EavStoreViewString(
  var productId: Int,
  var storeViewId: Int,
  var attributeKey: String,
  var value: String
)

data class EavStoreViewInt(
  var productId: Int,
  var storeViewId: Int,
  var attributeKey: String,
  var value: Int
)

data class EavStoreViewMultiSelect(
  var productId: Int,
  var storeViewId: Int,
  var attributeKey: String,
  var value: Int
)

data class EavStoreViewMoney(
  var productId: Int,
  var storeViewId: Int,
  var attributeKey: String,
  var value: Double
)

data class EavStoreViewInfo(
  val eav: Eav,
  val eavStoreViewBool: EavStoreViewBool,
  val eavStoreViewFloat: EavStoreViewFloat,
  val eavStoreViewInt: EavStoreViewInt,
  val eavStoreViewString: EavStoreViewString,
  val eavStoreViewMultiSelect: EavStoreViewMultiSelect,
  val eavStoreViewMoney: EavStoreViewMoney
)

data class MultiSelectString(
  var attributeKey: String,
  var option: Int,
  var value: String
)

data class MultiSelectBool(
  var attributeKey: String,
  var option: Int,
  var value: Boolean
)

data class MultiSelectInt(
  var attributeKey: String,
  var option: Int,
  var value: Int
)

data class MultiSelectMoney(
  var attributeKey: String,
  var option: Int,
  var value: Double
)

data class MultiSelectFloat(
  var attributeKey: String,
  var option: Int,
  var value: Float
)

data class MultiSelectInfo(
  val multiSelectBool: MultiSelectBool,
  val multiSelectFloat: MultiSelectFloat,
  val multiSelectString: MultiSelectString,
  val multiSelectInt: MultiSelectInt,
  val multiSelectMoney: MultiSelectMoney,
)

data class FullProduct(
  var product: Products,
  var productsSeo: ProductsSeo,
  var productsPricing: ProductsPricing
)

data class EavGlobalInfo(
  val eav: Eav,
  val eavGlobalBool: Boolean,
  val eavGlobalFloat: Float,
  val eavGlobalString: String,
  val eavGlobalInt: Int,
  val eavGlobalMoney: Double,
  val eavGlobalMultiSelect: Int
)

enum class Type(name: String) {
  BOOL("bool"),
  FLOAT("float"),
  INT("int"),
  STRING("string"),
  MONEY("money"),
}
