import Foundation

@objc(CalendarModuleOther)
class CalendarModuleOther: RCTEventEmitter {
  
  public static var shared:CalendarModuleOther?

      override init() {
          super.init()
        CalendarModuleOther.shared = self
      }

      override func supportedEvents() -> [String]! {
          return [
              "EventCountOther"
          ]
      }
  
  private var count = 0;
  
  @objc
  func createCalendarEvent(_ name: String, location: String, callback:RCTResponseSenderBlock){
     count += 1;
    print("SWIFT: Pretending to create an event %@ at %@", name, location);
    callback([count]);
  }
  
  @objc
  func createCalendarEventPromise(_ name: String,
                                  location: String,
                                  resolver resolve: RCTPromiseResolveBlock,
                                  rejecter reject: RCTPromiseRejectBlock) -> Void {
     count += 1;
    print("SWIFT: Pretending to create an event promise %@ at %@", name, location);
    self.sendEvent(withName: "EventCountOther", body: count)
    resolve(count)
  }
}
