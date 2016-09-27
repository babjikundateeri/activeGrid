package com.imaginea.activegrid.core.services

import com.imaginea.activegrid.core.models.{ImageInfo, ImageInfoRepository}
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by babjik on 22/9/16.
  */
class CatalogService (implicit val executionContext: ExecutionContext) {
  val logger = Logger(LoggerFactory.getLogger(getClass.getName))

  implicit val imageDao: ImageInfoRepository = new ImageInfoRepository

  def getImages: Future[Option[List[ImageInfo]]] = Future{
    Some(imageDao.getImages())
  }

  def createImage(image: ImageInfo): Future[Option[ImageInfo]] = Future{
    logger.debug(s"images ${image.toString}")
    Some(imageDao.saveImage(image))
  }

  def updateImage(id: String, image: ImageInfo): Future[Option[ImageInfo]] = Future{
    logger.debug(s"images ${image.toString}")
    Some(imageDao.updateImage(id, image))
  }

  def deleteImage(id: String): Future[Option[String]]  = Future{
    logger.debug(s"deleting images with id ${id}")
    imageDao.deleteImage(id)
    Some("true")
  }
}
