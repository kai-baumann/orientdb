package com.orientechnologies.orient.core.storage.impl.local.paginated.wal.po.cellbtree.singlevalue.v1.cellbtreebucketsinglevalue;

import com.orientechnologies.common.serialization.types.OIntegerSerializer;
import com.orientechnologies.orient.core.storage.cache.OCacheEntry;
import com.orientechnologies.orient.core.storage.impl.local.paginated.wal.WALRecordTypes;
import com.orientechnologies.orient.core.storage.impl.local.paginated.wal.po.PageOperationRecord;
import com.orientechnologies.orient.core.storage.index.sbtree.singlevalue.v1.OCellBTreeBucketSingleValue;

import java.nio.ByteBuffer;

public class CellBTreeBucketSingleValueV1SetLeftSiblingPO extends PageOperationRecord {
  private int prevLeftSibling;
  private int leftSibling;

  public CellBTreeBucketSingleValueV1SetLeftSiblingPO() {
  }

  public CellBTreeBucketSingleValueV1SetLeftSiblingPO(int prevLeftSibling, int leftSibling) {
    this.prevLeftSibling = prevLeftSibling;
    this.leftSibling = leftSibling;
  }

  public int getPrevLeftSibling() {
    return prevLeftSibling;
  }

  public int getLeftSibling() {
    return leftSibling;
  }

  @Override
  public void redo(OCacheEntry cacheEntry) {
    final OCellBTreeBucketSingleValue bucket = new OCellBTreeBucketSingleValue(cacheEntry);
    bucket.setLeftSibling(leftSibling);
  }

  @Override
  public void undo(OCacheEntry cacheEntry) {
    final OCellBTreeBucketSingleValue bucket = new OCellBTreeBucketSingleValue(cacheEntry);
    bucket.setLeftSibling(prevLeftSibling);
  }

  @Override
  public byte getId() {
    return WALRecordTypes.CELL_BTREE_BUCKET_SINGLE_VALUE_V1_SET_LEFT_SIBLING_PO;
  }

  @Override
  public int serializedSize() {
    return super.serializedSize() + 2 * OIntegerSerializer.INT_SIZE;
  }

  @Override
  protected void serializeToByteBuffer(ByteBuffer buffer) {
    super.serializeToByteBuffer(buffer);

    buffer.putInt(prevLeftSibling);
    buffer.putInt(leftSibling);
  }

  @Override
  protected void deserializeFromByteBuffer(ByteBuffer buffer) {
    super.deserializeFromByteBuffer(buffer);

    prevLeftSibling = buffer.getInt();
    leftSibling = buffer.getInt();
  }
}
