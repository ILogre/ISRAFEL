package fr.unice.i3s.dslintegrator.domains.datacenter.mm

import fr.unice.i3s.dslintegrator.domains.{Domain, MMConcept}

class Catalog(name: String, val ress: List[Resource] = List(),override var log:String = "\n", notification:String = "\n") extends MMConcept with Domain {
  def addResource(uri: String, semantic: String, elements: (String, String)*) : Catalog = {
    val elems = elements.map( p => new Element(p._1,p._2)).toList
    new Catalog(this.name, new Resource(uri,semantic, elems) :: this.ress, this.log, this.notification)
  }
  def isDefined(uri : String) : Boolean = !this.ress.filter(r => r.uri == uri ).isEmpty

  override def toString() = "\nCatalog " + this.name + " { \n\tResources "+ress+"\n\t}\n"+log+notification

}
class Resource(val uri: String, semantic: String, elements: List[Element]) extends MMConcept{
  override def toString() = "\n\t\tres " + this.uri + " means " + this.semantic + " { \n\t\tElements "+this.elements+"\n\t\t}"
}
class Element(field: String, kind: String) extends MMConcept{
  override def toString() = "\n\t\t\t{ "+this.field+" , "+this.kind+" } "
}