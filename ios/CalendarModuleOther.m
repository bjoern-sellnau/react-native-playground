#import <Foundation/Foundation.h>
#import "React/RCTBridgeModule.h"

@interface RCT_EXTERN_MODULE(CalendarModuleOther,NSObject);
RCT_EXTERN_METHOD(createCalendarEvent:(NSString *)name location:(NSString *)location callback:(RCTResponseSenderBlock)callback)

RCT_EXTERN_METHOD(createCalendarEventPromise:(NSString *)name location:(NSString *)location resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

@end
